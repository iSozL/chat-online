import React, { useContext, useRef, useState, useEffect } from 'react';
import {changeUserContext, CHANGE_USER} from '../store/index'
import './index.scss'
import { Button, Popover, message as Msg} from 'antd'
import { Scrollbars, scrollToBottom } from 'react-custom-scrollbars'
import 'emoji-mart/css/emoji-mart.css'
import { Picker } from 'emoji-mart'
import request from '../../../utils/request'

if (!window.WebSocket) {
  window.WebSocket = window.MozWebSocket;
}
const Message = (props: any) => {
  let socket = props.socket
  const { userMsg, useDispatch } = useContext(changeUserContext)
  console.log(userMsg.msgs,'message')
  let info: any = JSON.parse(window.localStorage.getItem("userInfo"))
  // if (window.WebSocket) {
  //   if (info && !socket) {
  //     socket = new WebSocket(`ws://47.102.214.67:8080/ET_war/websocket/${info.userId}`);
  //   }
  // } else {
  //   alert("你的浏览器不支持 WebSocket！");
  // }
  // if (socket) {
  //   socket.onmessage = function (event: any) {
  //     event = JSON.parse(event.data)
  //     if (event.sender === info.userId) {
  //       console.log(event)
  //       let data = {
  //         message: event.message,
  //         time: new Date(),
  //         my: true,
  //         tag: event.receiver,
  //         tag1: event.sender
  //       }
  //       useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: true, msgs: userMsg.msgs.concat(data)}})
  //     } else {
  //       console.log(userMsg.msgs)
  //       let data = {
  //         message: event.message,
  //         time: new Date(),
  //         my: false,
  //         tag: event.receiver,
  //         tag1: event.sender
  //       }
  //       useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: true, msgs: userMsg.msgs.concat(data)}})
  //     }
  //   };
  //   socket.onopen = function (event: any) {
  //     console.log("连接开始")
  //   };
  //   socket.onclose = function (event: any) {
  //     console.log("连接关闭")
  //   };
  // }

  const inputs = useRef<any>()
  const send = (message: any) => {
    if (!message) {
      Msg.info("请输入内容")
      return
    }
    if (!window.WebSocket) {
      return;
    }
    if (socket.readyState == WebSocket.OPEN) {
      let data = {
        receiver: userMsg.userId,
        message
      }
      socket.send(JSON.stringify(data));
      let datas = {
        message: message,
        time: new Date(),
        my: true,
        tag: userMsg.userId,
        tag1: info.userId
      }
      useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: userMsg.show, msgs: userMsg.msgs.concat(datas)}})
      inputs.current.value = ""
    } else {
      alert("连接没有开启.");
    }
  }

  // useEffect(() => {
  //   console.log('?')
  //   window.addEventListener("keydown", function(e) {
  //     if(e.code === "Enter") {
  //       send(inputs.current.value)
  //     }
  //   })
  // }, []);
  const isEnter = (e: any) => {
    if(e.keyCode === 13) {
      e.preventDefault()
      send(inputs.current.value)
    }
  }

  console.log(userMsg.show)
  const getHistory = async () => {
    await request.post(`http://47.102.214.67:8080/ET_war/GetChatRecord?first=${info.userId}&second=${userMsg.userId}`).then(value => {
      let send: object[] = []
      let receive: object[] = []
      let arr = value.data.map((item: any) => {
        if (item.sender === info.userId) {
          let data = {
            message: item.message,
            time: item.time,
            my: true,
            tag: item.receiver,
            tag1: item.sender
          }
          return data
          // useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: userMsg.show, msgs: userMsg.msgs.concat(data)}})
        } else {
          let data = {
            message: item.message,
            time: item.time,
            my: false,
            tag: item.receiver,
            tag1: item.sender
          }
          return data
          // useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: userMsg.show, msgs: userMsg.msgs.concat(data)}})
        }
      })
      console.log(arr)
      useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: userMsg.show, msgs: arr}})
      console.log(send, receive)
    })
  }

  let { scrollToBottom } = new Scrollbars()
  const test =() => {
    // scrollToBottom()
    console.log('?')
  }
  const emoji = (
    <div>
      <Picker onSelect={(emoji: any)=> {inputs.current.value += emoji.native}} />
    </div>
  )
  return (
    <div className="msg-container">
      {
        userMsg.show ? 
        <>
        <div className="msg-body">
          <div className="msg-header">
            {userMsg.username}
          </div>
          <div className="history" onClick={getHistory}>
            <img style={{width: "20px"}} src={require('../../../assets/imgs/history.svg')} />
            查看历史聊天记录
          </div>
          <div>
            <Scrollbars style={{height: "48vh", width: "99%", display: "flex"}}>
              {
                userMsg.msgs.map((item: any, index: number) => {
                  if (!item.my && (item.tag1 === userMsg.userId)) {
                    return (
                      <div style={{padding: "10px 30px"}} key={index}>
                        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                        <div className="arrow-box popper border arrow-left">
                          {item.message}
                        </div>
                      </div>
                    )
                  } else if (item.tag === userMsg.userId && item.tag1 === info.userId) {
                    return (
                      <div style={{padding: "10px 30px", flex: 1, textAlign: "right"}} key={index}>
                        <div className="arrow-box popper border arrow-right">
                          {item.message}
                        </div>
                        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                      </div>
                    )
                  }
                })
              }
            </Scrollbars>
          </div>
        </div>
        <div className="msg-footer">
          <div className="footer-top">
            <Popover content={emoji} placement="leftTop">
              <img style={{width: "30px", height: "30px"}} src={require('../../../assets/imgs/face.svg')}></img>
            </Popover>
          </div>
          <div className="footer-body">
            <textarea className="ant-input" ref={inputs} onKeyDown={(e) => {isEnter(e)}} />
            <Button style={{float: 'right', marginTop: "10px"}} type="primary" onClick={() =>send(inputs.current.value)}>发送</Button>
          </div>
          <div className="footer-bottom">
          </div>
        </div>
      </> :
        <div style={{width: "100%", height: "100%", display: "flex", justifyContent: "center", alignItems: "center"}}>
          <img className="none-msg" src={require('../../../assets/imgs/msg-bg.svg')} />
        </div>
      }
    </div>
  )
}

export default Message;
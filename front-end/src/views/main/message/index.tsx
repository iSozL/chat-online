import React, { useContext, useRef, useState, useEffect } from 'react';
import {changeUserContext, CHANGE_USER} from '../store/index'
import './index.scss'
import { Button, Popover, message as Msg} from 'antd'
import Scrollbars from 'react-custom-scrollbars'
import 'emoji-mart/css/emoji-mart.css'
import { Picker } from 'emoji-mart'
import request from '../../../utils/request'
if (!window.WebSocket) {
  window.WebSocket = window.MozWebSocket;
}
const Message = (props: any) => {
  let socket = props.socket
  let scroll = useRef<any>()
  async function getMsgList() {
    await request.get(`http://101.132.134.186:8080/ShowFriendLastMessage?userId=${info.userId}`).then(value => {
      if (value.data.code) {
        console.log(value.data.data)
        console.log(userMsg.msgs)
        useDispatch({type: CHANGE_USER, state:{msgList: value.data.data}})
      }
    })
  }
  const { userMsg, useDispatch } = useContext(changeUserContext)
  let info: any = JSON.parse(window.localStorage.getItem("userInfo"))

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
      useDispatch({type: CHANGE_USER, state:{msgs: userMsg.msgs.concat(datas)}})
      inputs.current.value = ""
      setTimeout(() => {
        getMsgList()
      }, 100)
      setTimeout(() => {
        scroll.current.scrollToBottom()
      }, 1)
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

  useEffect(() => {
    if (scroll.current) {
      setTimeout(() => {
        scroll.current.scrollToBottom()
      }, 1)
    }
  })
  const getHistory = async () => {
    await request.post(`http://47.102.214.67:8080/ET_war/GetChatRecord?first=${info.userId}&second=${userMsg.userId}`).then(value => {
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
        } else {
          let data = {
            message: item.message,
            time: item.time,
            my: false,
            tag: item.receiver,
            tag1: item.sender
          }
          return data
        }
      })
      useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: userMsg.show, msgs: arr}})
      setTimeout(() => {
        scroll.current.scrollToBottom()
      }, 1)
    })
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
            <Scrollbars style={{height: "48vh"}} ref={scroll}>
              <div style={{display: "flex", flexDirection: "column"}}>
                {
                  userMsg.msgs.map((item: any, index: number) => {
                    if (!item.my && (item.tag1 === userMsg.userId)) {
                      return (
                        <div style={{padding: "10px 30px"}} key={index}>
                          <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                          <div className="arrow-box popper border arrow-left" style={{wordBreak: "break-all", wordWrap: "break-word"}}>
                            <div style={{wordBreak: "break-all", wordWrap: "break-word"}}>{item.message}</div>
                          </div>
                        </div>
                      )
                    } else if (item.tag === userMsg.userId && item.tag1 === info.userId) {
                      return (
                        <div style={{padding: "10px 30px", alignSelf: "flex-end"}} key={index}>
                          <div className="arrow-box popper border arrow-right" style={{wordBreak: "break-all", wordWrap: "break-word"}}>
                            <div style={{wordBreak: "break-all", wordWrap: "break-word"}}>{item.message}</div>
                          </div>
                          <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                        </div>
                      )
                    }
                  })
                }
              </div>
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
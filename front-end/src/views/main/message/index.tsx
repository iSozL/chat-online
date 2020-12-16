import React, { useContext, useRef, useEffect } from 'react';
import {changeUserContext, CHANGE_USER} from '../store/index'
import './index.scss'
import { Button, Popover } from 'antd'
import Scroll from 'react-custom-scrollbars'
import 'emoji-mart/css/emoji-mart.css'
import { Picker } from 'emoji-mart'

let client: string;
let socket: any;
if (!window.WebSocket) {
  window.WebSocket = window.MozWebSocket;
}
if (window.WebSocket) {
  socket = new WebSocket("ws://47.102.214.67:8080/ws");
} else {
  alert("你的浏览器不支持 WebSocket！");
}
const Message = () => {
  const { userMsg, useDispatch } = useContext(changeUserContext)

  socket.onmessage = function (event: any) {
    event = JSON.parse(event.data)
    console.log(event)
    if (!event.init) {
      client = event.client
      return
    }
    if (event.client === client) {
      let data = {
        message: event.message,
        time: new Date(),
        my: true
      }
      useDispatch({type: CHANGE_USER, state:{username: '孙笑川', show: true, msgs: userMsg.msgs.concat(data)}})
    } else {
      let data = {
        message: event.message,
        time: new Date(),
        my: false
      }
      useDispatch({type: CHANGE_USER, state:{username: '孙笑川', show: true, msgs: userMsg.msgs.concat(data)}})
    }
  };
  socket.onopen = function (event: any) {
    console.log("连接开始")
  };
  socket.onclose = function (event: any) {
    console.log("连接关闭")
  };

  

  const inputs = useRef<any>()

  const send = (message: any) => {
    if (!window.WebSocket) {
      return;
    }
    if (socket.readyState == WebSocket.OPEN) {
      socket.send(message);
      inputs.current.value = ""
    } else {
      alert("连接没有开启.");
    }
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
          <div>
            <Scroll style={{height: "48vh", width: "99%", display: "flex"}}>
              {
                userMsg.msgs.map((item: any, index: number) => {
                  if (!item.my) {
                    return (
                      <div style={{padding: "10px 30px"}} key={index}>
                        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                        <div className="arrow-box popper border arrow-left">
                          {item.message}
                        </div>
                      </div>
                    )
                  } else {
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
            </Scroll>
          </div>
        </div>
        <div className="msg-footer">
          <div className="footer-top">
            <Popover content={emoji} placement="leftTop">
              <img style={{width: "30px", height: "30px"}} src={require('../../../assets/imgs/face.svg')}></img>
            </Popover>
          </div>
          <div className="footer-body">
            <textarea className="ant-input" ref={inputs} />
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
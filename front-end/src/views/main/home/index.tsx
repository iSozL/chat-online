import React, { useState, useEffect, useContext, useLayoutEffect } from 'react';
import './index.scss'
import FriendContent from '../friendContent/index';
import Message from '../message/index'
import { Container } from '../store/index'
import AddFriend from '../addFriend/index'
import { Popover, Switch } from "antd"; 
import Setting from '../setting/index'
import { useHistory } from 'react-router-dom'
import {changeUserContext, CHANGE_USER} from '../store/index'
import request from '../../../utils/request'
import Comments from '../commets/index'
const blackStyle = {
  background: "#0000009e",
}
let socket: any
const Home: React.FC = () => {
  const history = useHistory()
  const { userMsg, useDispatch } = useContext(changeUserContext)
  const [adds, setAdd] = useState<object[]>()
  const [isRead, setRead] = useState<string[]>([])
  const logout = () => {
    window.localStorage.clear()
    socket.close()
    location.reload()
  }
  let info: any = JSON.parse(window.localStorage.getItem("userInfo"))
  if (window.WebSocket) {
    if (info && !socket) {
      socket = new WebSocket(`ws://47.102.214.67:8080/ET_war/websocket/${info.userId}`);
    }
  } else {
    alert("你的浏览器不支持 WebSocket！");
  }
  
  const [select, setSelect] = useState<string>("me")
  const menu = (
    <div className="menu">
      <div onClick={() => {setSelect("setting")}}>个人设置</div>
      <div onClick={logout}>退出登录</div>
    </div>
  )

  const changeBlack = (value: boolean) => {
    useDispatch({type: CHANGE_USER, state:{black: value}})
  }
  
  useEffect(() => {
    const fetchAdds = async () => {
      await request.post(`http://101.132.134.186:8080/showveritymessage`, {
        userId: info.userId
      }).then(value => {
        if (value.data.code) {
          setAdd(value.data.data)
        }
      })
    }
    fetchAdds()
  }, [])

  return (
    <div className="main">
      <div className="main-container" style={userMsg.black ? {borderRadius: 0} : {}}>
        <div className="aside" style={userMsg.black ? blackStyle : {}}>
          <div className="avater">
            <img onClick={() => {setSelect("me")}} src={require('../../../assets/imgs/avater.svg')} />
          </div>
          <div>
            {JSON.parse(window.localStorage.getItem("userInfo")).nickname}
          </div>
          <div>
            <img className="me" onClick={() => {setSelect("add")}} src={require('../../../assets/imgs/me.svg')} />
          </div>
          <div>
            <img className="comments" onClick={() => {setSelect("comments")}} src={require('../../../assets/imgs/comments.svg')} />
          </div>
          <div style={{marginTop: "50px"}}>
            <Switch onChange={changeBlack} />
          </div>
          <div>
            黑夜
          </div>
          <Popover placement="left" content={menu}>
            <img className="me" style={{marginTop: "20vh"}} src={require('../../../assets/imgs/more.svg')} />
          </Popover>
        </div>
        <div className="main-body" style={userMsg.black ? blackStyle : {}}>
          {
            (() => {
              switch(select) {
                case "me":
                  return (
                    <>
                      <FriendContent unread={isRead} setRead={setRead} adds={adds} socket={socket} />
                      <Message socket={socket} />
                    </>
                  )
                case "add":
                  return (
                    <>
                      <AddFriend socket={socket} />
                    </>
                  )
                case "setting":
                  return (
                    <>
                      <Setting />
                    </>
                  )
                case "comments":
                  return (
                    <>
                      <Comments />
                    </>
                  )
                default:
                  return (
                    <>
                      <FriendContent />
                      <Message />
                    </>
                  )
              }
            })()
          }
        </div>
      </div>
    </div>
  )
}

export default Home
import React, { useContext } from 'react';
import './index.scss'
import { Tabs, Popover } from 'antd';
import Scroll from 'react-custom-scrollbars';
import { changeUserContext, CHANGE_USER } from '../store/index'

const { TabPane } = Tabs;

const detail = (
  <div>
    <div>孙笑川</div>
    <div>带带大师兄</div>
    <div>大家笑一笑就好</div>
  </div>
)

const FriendsList = () => {
  const { userMsg, useDispatch } = useContext(changeUserContext)
  return (
    <Scroll>
      <Popover content={detail} placement="right">
        <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: '孙笑川', userId: '101', show: true, msgs: userMsg.msgs}})}}>
          <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
          <div className="msg">
            <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>孙笑川</div>
          </div>
        </div>
      </Popover>
    </Scroll>
  )
}

const MsgList = () => {
  const { userMsg, useDispatch } = useContext(changeUserContext)
  return (
    <Scroll>
      <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: '12', userId: '101', show: true, msgs: userMsg.msgs}})}}>
        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
        <div className="msg">
          <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>12</div>
          <div style={{paddingLeft: "5px"}}>我说你妈妈死了</div>
        </div>
      </div>
      <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: 'sansiuchuang1', userId: '32292370', show: true, msgs: userMsg.msgs}})}}>
        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
        <div className="msg">
          <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>sansiuchuang1</div>
          <div style={{paddingLeft: "5px"}}>哦，还有这种事</div>
        </div>
      </div>
      <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: 'blackdog', userId: '10255976', show: true, msgs: userMsg.msgs}})}}>
        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
        <div className="msg">
          <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>blackdog</div>
          <div style={{paddingLeft: "5px"}}>都homie</div>
        </div>
      </div>
    </Scroll>
  )
}

const FriendContent = () => {
  return (
    <div className="friend-container">
      <div className="unread">
        <img style={{width: "20px"}} src={require('../../../assets/imgs/smile.svg')} />未读消息
      </div>
      <div className="content">
        <Tabs defaultActiveKey="1">
          <TabPane
            tab={
              <span>
                消息
              </span>
            }
            key="1"
          >
            <div style={{height: "420px", display: "flex", flexDirection: "column", alignItems: "center", padding: "5px 0"}}>
              <MsgList />
            </div>
          </TabPane>
          <TabPane
            tab={
              <span>
                好友
              </span>
            }
            key="2"
          >
            <div style={{height: "420px", display: "flex", flexDirection: "column", alignItems: "center", padding: "5px 0"}}>
              <FriendsList />
            </div>
          </TabPane>
        </Tabs>
      </div>
    </div>
  )
}

export default FriendContent
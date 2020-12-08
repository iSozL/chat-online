import React, { useContext } from 'react';
import './index.scss'
import { Tabs } from 'antd';
import Scroll from 'react-custom-scrollbars';
import { changeUserContext, CHANGE_USER } from '../store/index'

const { TabPane } = Tabs;

const MsgList = () => {
  const { userMsg, useDispatch } = useContext(changeUserContext)
  return (
    <Scroll>
      <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: '孙笑川', show: true}})}}>
        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
        <div className="msg">
          <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>孙笑川</div>
          <div style={{paddingLeft: "5px"}}>我说你妈妈死了</div>
        </div>
      </div>
      <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: '孙笑川', show: true}})}}>
        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
        <div className="msg">
          <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>孙笑川</div>
          <div style={{paddingLeft: "5px"}}>我说你妈妈死了</div>
        </div>
      </div>
      <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: '孙笑川', show: true}})}}>
        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
        <div className="msg">
          <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>孙笑川</div>
          <div style={{paddingLeft: "5px"}}>我说你妈妈死了</div>
        </div>
      </div>
      <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: '孙笑川', show: true}})}}>
        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
        <div className="msg">
          <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>孙笑川</div>
          <div style={{paddingLeft: "5px"}}>我说你妈妈死了</div>
        </div>
      </div>
      <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: '孙笑川', show: true}})}}>
        <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
        <div className="msg">
          <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>孙笑川</div>
          <div style={{paddingLeft: "5px"}}>我说你妈妈死了</div>
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
            Tab 2
          </TabPane>
        </Tabs>
      </div>
    </div>
  )
}

export default FriendContent
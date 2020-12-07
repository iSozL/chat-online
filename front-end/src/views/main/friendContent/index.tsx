import * as React from 'react';
import './index.scss'
import { Tabs } from 'antd';

const { TabPane } = Tabs;

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
            <div style={{display: "flex", justifyContent: "center", flexDirection: "column", alignItems: "center"}}>
              <div className="user-msg">
                1
              </div>
              <div className="user-msg">
                1
              </div>
              <div className="user-msg">
                1
              </div>
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
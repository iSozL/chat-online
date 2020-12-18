import React, { useContext, useState, useEffect } from 'react';
import './index.scss'
import { Tabs, Popover, Modal, Button, Form, Select, Input, message } from 'antd';
import Scroll from 'react-custom-scrollbars';
import { changeUserContext, CHANGE_USER } from '../store/index'
import request from '../../../utils/request'

const {Option} = Select
let info = JSON.parse(window.localStorage.getItem("userInfo"))
const layout = {
  labelCol: { span: 6 },
  wrapperCol: { span: 16 },
};
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

const MsgList = (props: any) => {
  let unreads = props.unread
  let setRead = props.setRead
  const { userMsg, useDispatch } = useContext(changeUserContext)
  const [msgList, setList] = useState<object[]>()
  const deleteRead = (userId: string) => {
    if (unreads && unreads.indexOf(userId) !== -1) {
      return unreads.filter((item: string) => {
        return item !== userId
      })
    }
  }
  useEffect(() => {
    async function getMsgList() {
      await request.get(`http://101.132.134.186:8080/ShowFriendLastMessage?userId=${info.userId}`).then(value => {
        if (value.data.code) {
          setList(value.data.data)
        }
      })
    }
    getMsgList()
  }, [])
  return (
    <Scroll>
      {
        msgList && (msgList instanceof Array) ? 
        msgList.map((item: any, index: number) => {
          return (
            <div key={index} className="user-msg" onClick={() => {setRead(deleteRead(item.friendId));useDispatch({type: CHANGE_USER, state:{username: item.nickname, userId: item.friendId, show: true, msgs: userMsg.msgs}})}}>
              <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
              <div className="msg">
                <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>
                  {item.nickname}
                </div>
                <div style={{paddingLeft: "5px"}}>{item.messagetxt}</div>
              </div>
              {
                unreads && unreads.indexOf(item.friendId) > -1 ? 
                <div className="has-msg">
                  .
                </div> :
                <div></div>
              }
            </div>
          )
        })
        : ""
      }
    </Scroll>
  )
}

const FriendContent = (props: any) => {
  const [visible, setVisible] = useState(false);
  const [visible1, setVisible1] = useState(false);
  const [curId, setId] = useState()
  console.log(info.groups)
  const send = async (value: any) => {
    let datas = {
      userId: info.userId,
      sendId: curId,
      ...value
    }
    const {data} = await request.post(`http://101.132.134.186:8080/handlemessage`, {
      ...datas
    })

    if (data.code) {
      message.success(data.message)
      setVisible1(false)
    } else {
      message.error(data.message)
    }
  }
  return (
    <div className="friend-container">
      <Modal
        title="设置信息"
        visible={visible1}
        onOk={() => {setVisible1(false)}}
        onCancel={() => {setVisible1(false)}}
      >
        <Form {...layout} onFinish={send}>
          <Form.Item name="groupname" label="选择列表">
            <Select style={{ width: 120 }}>
              {
                info.groups && typeof info.groups === "object" ? info.groups.map((item: any, index: number) => {
                  return (
                    <Option value={item.groupname} key={index}>
                      {item.groupname}
                    </Option>
                  )
                }) : ""
              }
            </Select>
          </Form.Item>
          <Form.Item name="note" label="备注">
            <Input width={100} />
          </Form.Item>
          <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 6 }}>
            <Button type="primary" htmlType="submit">
              添加
            </Button>
          </Form.Item>
        </Form>
      </Modal>

      <Modal 
        title="好友申请"
        visible={visible}
        onOk={() => setVisible(false)}
        onCancel={() => setVisible(false)}
      >
        <Scroll style={{height: "200px"}}>
          {
            typeof props.adds === 'object' ?
            props.adds.map((item: any, index: number) => {
              return (
                <div className="req" key={index}>
                  <img style={{width: "70px", paddingLeft: "10px"}} src={require('../../../assets/imgs/avater.svg')} />
                  <div style={{height: "70px", paddingLeft: "10px"}}>
                    <div>用户id：{item.sendid}</div>
                    <div>验证信息：{item.messagetext}</div>
                    <div>时间：{item.sendtime}</div>
                  </div>
                  <div style={{marginLeft: "10%"}}>
                    <Button type="primary" onClick={() => {setVisible1(true); setId(item.sendid)}}>接受</Button>
                  </div>
                  <div style={{marginLeft: "10px"}}>
                    <Button type="primary" danger>拒绝</Button>
                  </div>
                </div>
              )
            }) : ""
          }
        </Scroll>
      </Modal>
      <div className="unread">
        <img style={{width: "20px"}} src={require('../../../assets/imgs/smile.svg')} />
        {
          typeof props.adds === 'object' ? <div onClick={() => {setVisible(true)}}>有好友申请</div> : ""
        }
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
              <MsgList unread={props.unread} setRead={props.setRead} />
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
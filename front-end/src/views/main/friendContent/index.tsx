import React, { useContext, useState, useEffect } from 'react';
import './index.scss'
import { Tabs, Popover, Modal, Button, Form, Select, Input, message, Menu } from 'antd';
import Scroll from 'react-custom-scrollbars';
import { changeUserContext, CHANGE_USER } from '../store/index'
import request from '../../../utils/request'

const { SubMenu } = Menu
const { Option } = Select
const layout = {
  labelCol: { span: 6 },
  wrapperCol: { span: 16 },
};
const { TabPane } = Tabs;
const Detail = (props: any) => {
  let msg = props.msg

  const hasProperty = (property: undefined | string): string => {
    if (!property) {
      return ""
    } else {
      return String(property)
    }
  }

  return (
    <div>
      <div>昵称：{hasProperty(msg.nickname)}</div>
      <div>账号：{hasProperty(msg.userId)}</div>
      <div>个性签名：{hasProperty(msg.signature)}</div>
      <div>地址：{hasProperty(msg.address)}</div>
      <div>电话：{hasProperty(msg.phone)}</div>
      <div>印象：{hasProperty(msg.evaluate)}</div>
    </div>
  )
}

// const FriendsList = () => {
//   let info = JSON.parse(window.localStorage.getItem("userInfo"))
//   let groups = info.groups
//   const [friendList, setList] = useState<object[]>([])
//   useEffect(() => {
//     async function getFriendList() {
//       await request.get(`http://101.132.134.186:8080/GroupFriends?userId=${info.userId}`).then(value => {
//         if (value.data.code) {
//           for (let i in value.data.data) {
//             for (let j in groups) {
//               if (value.data.data[i].groupname === groups[j].groupname) {
//                 typeof groups[j].list === "object" ? groups[j].list.push(value.data.data[i]) : groups[j].list = [value.data.data[i]]
//               }
//             }
//           }
//           setList(groups)
//         } else {
//           message.error(value.data.message)
//         }
//       })
//     }
//     getFriendList()
//   }, [])

//   return (
//     <Scroll>
//       <Menu
//         style={{ width: "100%" }}
//         defaultSelectedKeys={['0']}
//         mode="inline"
//       >
//         {
//           friendList.map((item: any, index: number) => {
//             return (
//               <SubMenu key={index} title={item.groupname} popupClassName="menu-item">
//                 {
//                   typeof item.list === "object" ? 
//                   item.list.map((i: any, index: number) => {
//                     return (
//                       <div key={index}>
//                         <Popover content={<Detail msg={i} />} placement="right">
//                           <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: i.nickname, userId: i.userId, show: true, msgs: userMsg.msgs}})}}>
//                             <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
//                             <div className="msg">
//                               <div style={{padding: "5px 0 0 5px", fontSize: "18px", lineHeight: "1.5"}}>{i.nickname}{ typeof i.note === "string" ? `(${i.note})` : "" }</div>
//                               <div style={{paddingLeft: "5px", lineHeight: "1.5"}}>{ typeof i.signature === "string" ? i.signature : ""}</div>
//                             </div>
//                           </div>
//                         </Popover>
//                       </div>
//                     )
//                   }) : ""
//                 }
//               </SubMenu>
//             )
//           })
//         }
//       </Menu>
//     </Scroll>
//   )
// }

const MsgList = (props: any) => {
  let info = JSON.parse(window.localStorage.getItem("userInfo"))
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

  const [invate, setInvate] = useState<object[]>()

  const { userMsg, useDispatch } = useContext(changeUserContext)
  async function getFriendList() {
    await request.get(`http://101.132.134.186:8080/GroupFriends?userId=${info.userId}`).then(value => {
      if (value.data.code) {
        for (let i in value.data.data) {
          for (let j in groups) {
            if (value.data.data[i].groupname === groups[j].groupname) {
              typeof groups[j].list === "object" ? groups[j].list.push(value.data.data[i]) : groups[j].list = [value.data.data[i]]
            }
          }
        }
        setList(groups)
      } else {
        message.error(value.data.message)
      }
    })
  }
  let info = JSON.parse(window.localStorage.getItem("userInfo"))
  let adds
  // const selectAdds = async () => {
  //   if (typeof invate === 'object') {
  //     adds = invate.filter((item: object) => {
  //       return item.texttype === 0
  //     })
  //     console.log('?')
  //     if (typeof adds === 'object' && adds.length > 0) {
  //       setAdd(true)
  //     }
  //   }
  // }
  // selectAdds()
  const fetchAdds = async () => {
    await request.post(`http://101.132.134.186:8080/showveritymessage`, {
      userId: info.userId
    }).then(async (value) => {
      if (value.data.code) {
        if (value.data.data) {
          setInvate(value.data.data)
        }
      }
    })
  }
  const [visible, setVisible] = useState(false);
  const [visible1, setVisible1] = useState(false);
  const [curId, setId] = useState()
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
      getFriendList()
      setVisible1(false)
      fetchAdds()
      props.socket.send(JSON.stringify({
        receiver: curId,
        flag: 1
      }))
    } else {
      message.error(data.message)
    }
  }

  if (typeof invate === 'object') {
    adds = invate.filter((item: object) => {
      return item.texttype === 0
    })
  }

  // props.socket.onmessage = function(event: any) {
  //   event = JSON.parse(event.data)
  //   if (event.flag) {
  //     fetchAdds()
  //   }
  // }

  const [add, setAdd] = useState<object[]>()
  const [isRead, setRead] = useState<string[]>([])
  if (props.socket) {
    props.socket.onmessage = function (event: any) {
      event = JSON.parse(event.data)
      if (!event.flag) {
        if (event.sender === info.userId) {
          let data = {
            message: event.message,
            time: new Date(),
            my: true,
            tag: event.receiver,
            tag1: event.sender
          }
          useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: true, msgs: userMsg.msgs.concat(data)}})
        } else {
          console.log(isRead, event.sender)
          let newRead: any
          if (isRead) {
            newRead = isRead
          } else {
            newRead = []
          }
          if (newRead.indexOf(event.sender) === -1) {
            newRead.push(event.sender)
          }
          setRead(newRead)
          let data = {
            message: event.message,
            time: new Date(),
            my: false,
            tag: event.receiver,
            tag1: event.sender
          }
          useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: true, msgs: userMsg.msgs.concat(data)}})
        }
      } else {
        fetchAdds()
      }
    };
    props.socket.onopen = function (event: any) {
      console.log("连接开始")
    };
    props.socket.onclose = function (event: any) {
      console.log("连接关闭")
    };
  }
  let groups = info.groups
  const [friendList, setList] = useState<object[]>([])
  useEffect(() => {
    getFriendList(),
    fetchAdds()
  }, [])

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
                (info.groups && (info.groups instanceof Array)) ? info.groups.map((item: any, index: number) => {
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
            typeof adds === 'object' ?
            adds.map((item: any, index: number) => {
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
          <div onClick={() => {setVisible(true)}}>
            好友申请
            {
              typeof adds === 'object' && adds.length > 0 ? 
              <span style={{display: "inline-block", width: "10px", height: "10px", background: "red", borderRadius: "50%", marginLeft: "5px"}}>
              </span> :
              ""
            }
          </div>
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
              <MsgList unread={isRead} setRead={setRead} />
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
            <div style={{height: "70vh", display: "flex", flexDirection: "column", alignItems: "center", padding: "5px 0"}}>
              <Scroll>
                <Menu
                  style={{ width: "100%" }}
                  defaultSelectedKeys={['0']}
                  mode="inline"
                >
                  {
                    friendList.map((item: any, index: number) => {
                      return (
                        <SubMenu key={index} title={item.groupname} popupClassName="menu-item">
                          {
                            typeof item.list === "object" ? 
                            item.list.map((i: any, index: number) => {
                              return (
                                <div key={index}>
                                  <Popover content={<Detail msg={i} />} placement="right">
                                    <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: i.nickname, userId: i.userId, show: true, msgs: userMsg.msgs}})}}>
                                      <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                                      <div className="msg">
                                        <div style={{padding: "5px 0 0 5px", fontSize: "18px", lineHeight: "1.5"}}>{i.nickname}{ typeof i.note === "string" ? `(${i.note})` : "" }</div>
                                        <div style={{paddingLeft: "5px", lineHeight: "1.5"}}>{ typeof i.signature === "string" ? i.signature : ""}</div>
                                      </div>
                                    </div>
                                  </Popover>
                                </div>
                              )
                            }) : ""
                          }
                        </SubMenu>
                      )
                    })
                  }
                </Menu>
              </Scroll>
            </div>
          </TabPane>
        </Tabs>
      </div>
    </div>
  )
}

export default FriendContent
import React, { useContext, useState, useEffect } from 'react';
import './index.scss'
import { Tabs, Popover, Modal, Button, Form, Select, Input, message, Menu, Dropdown } from 'antd';
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

const Mes = (props: any) => {
  const [mes, setMes] = useState<object[]>()
  async function getMes(id: string) {
    await request.get(`http://101.132.134.186:8080/ShowFriendImage?friendId=${id}`).then(async value => {
      if (value.data.code) {
        setMes(value.data.data)
        console.log(mes)
      } else {
        message.error(value.data.message)
      }
    })
  }
  useEffect(() => {
    getMes(props.id)
  }, [])
  return (
    <div>
      <Scroll style={{height: "300px", background: "#d5d8db"}}>
        {
          mes && (mes instanceof Array)  ? 
          mes.map((item: any, index: number) => {
            return (
              <div key={index} className="user-mes">
                <div style={{fontSize: "18px"}}>{item.message}</div>
                <div style={{fontSize: "14px"}}>
                  {item.sendtime}
                  <span>删除</span>
                </div>
              </div>
            )
          })
          :
          "暂无好友印象"
        }
      </Scroll> 
    </div>
  )
}

const Detail = (props: any) => {
  let msg = props.msg
  const hasProperty = (property: undefined | string | number): string | number => {
    if (!property) {
      return ""
    } else {
      return String(property)
    }
  }

  return (
    <div style={{width: "200px"}}>
      <div>昵称：{hasProperty(msg.nickname)}</div>
      <div>性别：{hasProperty(msg.sex)}</div>
      <div>年龄：{hasProperty(msg.age)}</div>
      <div>账号：{hasProperty(msg.userId)}</div>
      <div>个性签名：{hasProperty(msg.signature)}</div>
      <div>地址：{hasProperty(msg.address)}</div>
      <div>电话：{hasProperty(msg.phone)}</div>
      <div>
        印象: <Mes id={msg.userId} />
      </div>
    </div>
  )
}

const FriendContent = (props: any) => {

  const [invate, setInvate] = useState<object[]>()

  const { userMsg, useDispatch } = useContext(changeUserContext)
  let groups: any
  async function getGroups() {
    await request.get(`http://101.132.134.186:8080/ShowGroup?userId=${info.userId}`).then(value => {
      if (value.data.code) {
        groups = value.data.data
        let newInfo = Object.assign(info, {groups: groups})
        window.localStorage.setItem("userInfo",JSON.stringify(newInfo))
      } else {
        message.error(value.data.message)
      }
    })
  } 
  async function getFriendList() {
    await getGroups()
    await request.get(`http://101.132.134.186:8080/GroupFriends?userId=${info.userId}`).then(value => {
      if (value.data.code) {
        for (let i in value.data.data) {
          for (let j in groups) {
            if (value.data.data[i].groupname === groups[j].groupname) {
              typeof groups[j].list === "object" ? groups[j].list.push(value.data.data[i]) : groups[j].list = [value.data.data[i]]
            }
          }
        }
        setLists(groups)
      } else {
        message.error(value.data.message)
      }
    })
  }
  let info = JSON.parse(window.localStorage.getItem("userInfo"))
  let adds
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
      setVisible(false)
      fetchAdds()
    } else {
      message.error(data.message)
    }
  }

  if (typeof invate === 'object') {
    adds = invate.filter((item: object) => {
      return item.texttype === 0
    })
  }

  const [isRead, setRead] = useState<string[]>([])
  if (props.socket) {
    props.socket.onmessage = function (event: any) {
      event = JSON.parse(event.data)
      if (!event.flag) {
        getMsgList()
        if (event.sender === info.userId) {
          let data = {
            message: event.message,
            time: new Date(),
            my: true,
            tag: event.receiver,
            tag1: event.sender
          }
          useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: userMsg.show, msgs: userMsg.msgs.concat(data)}})
        } else {
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
          useDispatch({type: CHANGE_USER, state:{username: userMsg.username, userId: userMsg.userId, show: userMsg.show, msgs: userMsg.msgs.concat(data)}})
        }
      } else {
        fetchAdds()
      }
    };
    props.socket.onopen = function (event: any) {
      console.log("连接开始")
    };
    props.socket.onclose = function (event: any) {
      message.error("连接关闭")
    };
  }
  // let groups = info.groups
  const [friendList, setLists] = useState<object[]>([])
  const [tabKey, setKey] = useState<string>("1")

  const [msgList, setList] = useState<object[]>()
  const deleteRead = (userId: string) => {
    if (isRead && isRead.indexOf(userId) !== -1) {
      return isRead.filter((item: string) => {
        return item !== userId
      })
    }
  }
  async function getMsgList() {
    await request.get(`http://101.132.134.186:8080/ShowFriendLastMessage?userId=${info.userId}`).then(value => {
      if (value.data.code) {
        useDispatch({type: CHANGE_USER, state:{msgList: value.data.data}})
      }
    })
  }

  const FriendOperate = (props: any) => {
    const { msg } = props
    const [move, setMove] = useState<boolean>(false)
    const [note, setNote] = useState<boolean>(false)
    const [image, setImage] = useState<boolean>(false)
    const deleteFriend = async () => {
      await request.get(`http://101.132.134.186:8080/DeleteFriend?userId=${info.userId}&friendId=${msg.userId}`).then(value => {
        if (value.data.code) {
          message.success(value.data.message)
        } else {
          message.error(value.data.message)
        }
        getFriendList()
      })
    }

    const groupMove = async (value: any) => {
      await request.get(`http://101.132.134.186:8080/GroupMove?userId=${info.userId}&friendId=${msg.userId}&preGroupname=${msg.groupname}&postGroupname=${value.groupname}`).then(value => {
        if (value.data.code) {
          message.success(value.data.message)
        } else {
          message.error(value.data.message)
        }
        setMove(false)
        getFriendList()
      })
    }
    
    const reNote = async (value: any) => {
      await request.get(`http://101.132.134.186:8080/ChangeNote?userId=${info.userId}&friendId=${msg.userId}&note=${value.note}`).then(value => {
        if (value.data.code) {
          message.success(value.data.message)
        } else {
          message.error(value.data.message)
        }
        setNote(false)
        getFriendList()
      })
    }

    const addImage = async (value: any) => {
      await request.post('http://101.132.134.186:8080/addImage', {
        userId: info.userId,
        friendId: msg.userId,
        mes: value.mes
      }).then(value => {
        if (value.data.code) {
          message.success(value.data.message)
        } else {
          message.error(value.data.message)
        }
        getFriendList()
      })
    }

    return (
      <div className="ope">
        <Modal
          title="移动分组"
          centered
          visible={move}
          onOk={() => setMove(false)}
          onCancel={() => setMove(false)}
          width={500}
        >
          <Form {...layout} onFinish={groupMove}>
            <Form.Item name="groupname" label="选择分组">
              <Select style={{ width: 120 }}>
                {
                  info.groups.map((item: any, index: number) => {
                    return (
                      <Option value={item.groupname} key={index}>
                        {item.groupname}
                      </Option>
                    )
                  })
                }
              </Select>
            </Form.Item>
            <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 6 }}>
              <Button type="primary" htmlType="submit">
                移动
              </Button>
            </Form.Item>
          </Form>
        </Modal>

        <Modal
          title="更改备注"
          centered
          visible={note}
          onOk={() => setNote(false)}
          onCancel={() => setNote(false)}
          width={500}
        >
          <Form {...layout} onFinish={reNote}>
            <Form.Item name="note" label="更改备注">
              <Input />
            </Form.Item>
            <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 6 }}>
              <Button type="primary" htmlType="submit">
                更改
              </Button>
            </Form.Item>
          </Form>
        </Modal>
        <Modal
          title="添加印象"
          centered
          visible={image}
          onOk={() => setImage(false)}
          onCancel={() => setImage(false)}
          width={500}
        >
          <Form {...layout} onFinish={addImage}>
            <Form.Item name="mes" label="添加印象">
              <Input />
            </Form.Item>
            <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 6 }}>
              <Button type="primary" htmlType="submit">
                添加
              </Button>
            </Form.Item>
          </Form>
        </Modal>
        <div onClick={deleteFriend}>删除好友</div>
        <div onClick={() => setMove(true)}>移动分组</div>
        <div onClick={() => setNote(true)}>更改备注</div>
        <div onClick={() => setImage(true)}>添加印象</div>
      </div>
    )
  }

  const GroupOperate = (props: {name: string}) => {
    const [add, setAdd] = useState<boolean>(false)
    const deleteGroup = async () => {
      await request.get(`http://101.132.134.186:8080/DelGroup?userId=${info.userId}&groupname=${props.name}`).then(value => {
        if (value.data.code) {
          message.success(value.data.message)
        } else {
          message.error(value.data.message)
        }
        getFriendList()
      })
    }
    const createGroup = async (value: any) => {
      await request.get(`http://101.132.134.186:8080/CreatGroup?userId=${info.userId}&groupname=${value.name}`).then(value => {
        if (value.data.code) {
          message.success(value.data.message)
        } else {
          message.error(value.data.message)
        }
        setAdd(false)
        getFriendList()
      })
    }
    return (
      <div className="ope">
        <Modal
          title="创建分组"
          centered
          visible={add}
          onOk={() => setAdd(false)}
          onCancel={() => setAdd(false)}
          width={500}
        >
          <Form {...layout} onFinish={createGroup}>
            <Form.Item name="name" label="分组名">
              <Input />
            </Form.Item>
            <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 6 }}>
              <Button type="primary" htmlType="submit">
                创建
              </Button>
            </Form.Item>
          </Form>
        </Modal>
        <div onClick={deleteGroup}>删除分组</div>
        <div onClick={() => {setAdd(true)}}>创建分组</div>
      </div>
    )
  }

  useEffect(() => {
    getFriendList()
    getMsgList()
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
        <Tabs defaultActiveKey="1" activeKey={tabKey}>
          <TabPane
            tab={
              <span style={{padding: "20px 0"}} onClick={() => {setKey("1"); getMsgList()}}>
                消息
              </span>
            }
            key="1"
          >
            <div style={{height: "420px", display: "flex", flexDirection: "column", alignItems: "center", padding: "5px 0"}}>
            <Scroll>
              {
                userMsg.msgList && (userMsg.msgList instanceof Array) ? 
                userMsg.msgList.map((item: any, index: number) => {
                  return (
                    <div key={index} className="user-msg" style={userMsg.username === item.nickname ? {backgroundColor: "rgb(224, 218, 218)"} : {}}  onClick={() => {setRead(deleteRead(item.friendId));useDispatch({type: CHANGE_USER, state:{username: item.nickname, userId: item.friendId, show: true, msgs: userMsg.msgs}})}}>
                      <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                      <div className="msg">
                        <div style={{padding: "5px 0 0 5px", fontSize: "18px"}}>
                          {item.nickname}
                        </div>
                        <div style={{paddingLeft: "5px"}}>
                          {
                            typeof item.messagetxt === "string" && item.messagetxt["length"] > 9 ? 
                            item.messagetxt.slice(0, 8) + "..." : 
                            item.messagetxt
                          }
                        </div>
                      </div>
                      {
                        isRead && isRead.indexOf(item.friendId) > -1 ? 
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
            </div>
          </TabPane>
          <TabPane
            tab={
              <span style={{padding: "20px 0"}} onClick={() => {setKey("2")}}>
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
                                  <Dropdown overlay={<FriendOperate msg={i} />} trigger={['contextMenu']} placement="topLeft">
                                    <div className="user-msg" onClick={() => {useDispatch({type: CHANGE_USER, state:{username: i.nickname, userId: i.userId, show: true, msgs: userMsg.msgs}});}}>
                                    <Popover content={<Detail msg={i} />} placement="right">
                                      <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                                    </Popover>
                                      <div className="msg">
                                        <div style={{padding: "5px 0 0 5px", fontSize: "18px", lineHeight: "1.5"}}>{i.nickname}{ typeof i.note === "string" ? `(${i.note})` : "" }</div>
                                        <div style={{paddingLeft: "5px", lineHeight: "1.5"}}>{ typeof i.signature === "string" ? i.signature : ""}</div>
                                      </div>
                                    </div>
                                  </Dropdown>
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
          <TabPane
            tab={
              <span style={{padding: "20px 0"}} onClick={() => {setKey("3")}}>
                分组
              </span>
            }
            key="3"
          >
            <div style={{height: "70vh", display: "flex", flexDirection: "column", alignItems: "center", padding: "5px 0"}}>
              {
                friendList.map((item: any, index: number) => {
                  return (
                    <Dropdown key={index} overlay={<GroupOperate name={item.groupname} />} trigger={['contextMenu']} placement="topLeft">
                      <div style={{width: "100%", height: "50px", background: "#fff", display: "flex", alignItems: "center", paddingLeft: "20px"}} key={index}>
                        { item.groupname }
                      </div>
                    </Dropdown>
                  )
                })
              }
            </div>
          </TabPane>
        </Tabs>
      </div>
    </div>
  )
}

export default FriendContent
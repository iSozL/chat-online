import React, {useState} from 'react';
import { Input, message, Button, Modal, Select, Form } from 'antd';
const { Search } = Input
import request from '../../../utils/request'
import './index.scss'
const { Option } = Select
const layout = {
  labelCol: { span: 6 },
  wrapperCol: { span: 16 },
};
const AddFriend = (props: any) => {
  let info = JSON.parse(window.localStorage.getItem("userInfo"))
  const [userContent, setContent] = useState<any>()
  const [visible, setVisible] = useState<boolean>(false);
  const [userGroup, setGroup] = useState<any>([{groupname: "请等待"}])
  const select = (value: string) => {
    console.log(value)
  }
  const preAdd = async () => {
    const {data} = await request.post(`http://101.132.134.186:8080/PreAddfriend`, {
      userId: info.userId,
      friendId: userContent.userId
    })
    if(data.code) {
      console.log(typeof(data.data[0].groupname))
      setGroup(data.data)
      setVisible(true)
    } else {
      message.error(data.message)
    }
  }
  const onSearch = async (value: string) => {
    const {data} = await request.get(`http://101.132.134.186:8080/find?userId=${value}`)
    if(data.code) {
      setContent(data.data)
      message.success(data.message)
    } else {
      message.error(data.message)
    }
  }

  const send = async (value: any) => {
    let datas = {
      userId: info.userId,
      friendId: userContent.userId,
      ...value
    }
    const {data} = await request.post('http://101.132.134.186:8080/addfriend', {
      ...datas
    })
    if (data.code) {
      message.success(data.message)
      setVisible(false)
      console.log(props.socket)
      props.socket.send(JSON.stringify({
        receiver: userContent.userId,
        flag: 1
      }))
    } else {
      message.error(data.message)
    }
  }

  return (
    <div className="search-container">
      <Search placeholder="请输入查找的 ID" onSearch={onSearch} style={{width: 600}} />
      <Modal
        title="添加好友"
        centered
        visible={visible}
        onOk={() => setVisible(false)}
        onCancel={() => setVisible(false)}
        width={500}
      >
        <Form {...layout} onFinish={send} initialValues={{groupname: "我的好友"}}>
          <Form.Item name="groupname" label="选择列表">
            <Select style={{ width: 120 }} onChange={select}>
              {
                userGroup.map((item: any, index: number) => {
                  return (
                    <Option value={item.groupname} key={index}>
                      {item.groupname}
                    </Option>
                  )
                })
              }
            </Select>
          </Form.Item>
          <Form.Item name="note" label="备注">
            <Input />
          </Form.Item>
          <Form.Item name="message" label="验证消息">
            <Input />
          </Form.Item>
          <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 6 }}>
            <Button type="primary" htmlType="submit">
              添加
            </Button>
          </Form.Item>
        </Form>
      </Modal>
      <div style={{width: "600px", height: "400px", marginTop: "60px"}} className="add-content">
        {
          userContent ? 
          <div className="add-msg">
            <div className="add-msg-item">
              <img style={{width: "70px", paddingLeft: "10px"}} src={require('../../../assets/imgs/avater.svg')} />
              <div style={{height: "50px", paddingLeft: "10px"}}>
                <div>昵称：{userContent.nickname}</div>
                <div>性别：{userContent.sex}</div>
              </div>
            </div>
            <div style={{marginRight: "10%"}}>
              <Button type="primary" onClick={preAdd}>添加好友</Button>
            </div>
          </div> :
          ""
        }
      </div>
    </div>
  )
}

export default AddFriend
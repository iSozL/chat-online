import React, {useState, useEffect} from 'react';
import { Form, Input, InputNumber, Button, message } from 'antd';
import './index.scss'
import request from '../../../utils/request'

const validateMessages = {
  required: '${label} is required!',
  types: {
    email: '${label} is not a valid email!',
    number: '${label} is not a valid number!',
  },
  number: {
    range: '${label} must be between ${min} and ${max}',
  },
};

const layout = {
  labelCol: { span: 6 },
  wrapperCol: { span: 16 },
};


const Setting = () => {
  let info = JSON.parse(window.localStorage.getItem("userInfo"))
  const [oldInfo, setInfo] = useState()
  async function getInfo() {
    await request.get(`http://101.132.134.186:8080/ShowInfo?userId=${info.userId}`).then(value => {
      if (value.data.code) {
        setInfo(value.data.data)
      } else {
        message.error(value.data.message)
      }
    })
  }
  async function updateInfo(value: any) {
    let datas = {
      userId: info.userId,
      nickname: typeof value.nickname === "string" ? value.nickname : oldInfo.nickname,
      age: typeof value.age === "string" ? Number(value.age) : oldInfo.age,
      sex: typeof value.sex === "string" ? value.sex: oldInfo.sex,
      address: typeof value.address === "string" ? value.address : oldInfo.address,
      signature: typeof value.signature === "string" ? value.signature : oldInfo.signature,
      phone: typeof value.phone === "string" ? value.phone : oldInfo.phone
    }
    await request.post(`http://101.132.134.186:8080/updateinfo`, {
      ...datas
    }).then(value => {
      if (value.data.code) {
        let newInfo = Object.assign(info, datas)
        window.localStorage.setItem("userInfo", JSON.stringify(newInfo))
        getInfo()
        message.success(value.data.message)
      } else {
        message.error(value.data.message)
      }
    })
  }
  useEffect(() => {
    getInfo()
  }, [])
  return (
    <div className="set-container">
      <Form {...layout} style={{width: "70%"}} name="nest-messages" onFinish={updateInfo} validateMessages={validateMessages}>
        <Form.Item label="账号">
          <Input placeholder={info.userId} disabled />
        </Form.Item>
        <Form.Item name="nickname" label="昵称">
          <Input placeholder={typeof oldInfo === 'object' && oldInfo !== null ? oldInfo.nickname : ""} />
        </Form.Item>
        <Form.Item name="sex" label="性别">
          <Input placeholder={typeof oldInfo === 'object' && oldInfo !== null ? oldInfo.sex : ""} />
        </Form.Item>
        <Form.Item name="age" label="年龄">
          <Input placeholder={typeof oldInfo === 'object' && oldInfo !== null ? oldInfo.age : ""} />
        </Form.Item>
        <Form.Item name="address" label="住址">
          <Input placeholder={typeof oldInfo === 'object' && oldInfo !== null ? oldInfo.address : ""} />
        </Form.Item>
        <Form.Item name="phone" label="电话">
          <Input placeholder={typeof oldInfo === 'object' && oldInfo !== null ? oldInfo.phone : ""} />
        </Form.Item>
        <Form.Item name="signature" label="个性签名">
          <Input placeholder={typeof oldInfo === 'object' && oldInfo !== null ? oldInfo.signature : ""} />
        </Form.Item>
        <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 6 }}>
          <Button type="primary" htmlType="submit">
            保存
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
}

export default Setting;
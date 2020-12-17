import React, { ReactElement, useState } from 'react';
import './index.less'
import { Form, Input, Button, Row, Col, Tooltip, Select, Cascader, AutoComplete, Checkbox, message } from 'antd';
import { UserOutlined, LockOutlined, QuestionCircleOutlined } from '@ant-design/icons';
import request from '../../utils/request';
import { useHistory } from 'react-router-dom'
const { Option } = Select;
const AutoCompleteOption = AutoComplete.Option;
let info: any = JSON.parse(window.localStorage.getItem("userInfo"))

interface Iprops {
  setLogin: React.Dispatch<React.SetStateAction<boolean>>
}

const Square: React.FC =() => {
  return (
    <ul className="bg-bubbles">
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
    </ul>
  )
}


const LoginBox = (props: Iprops) => {
  let history = useHistory()
  const { setLogin } = props
  const layout = {
    labelCol: { span: 6 },
    wrapperCol: { span: 16 },
  };
  const tailLayout = {
    wrapperCol: { offset: 6, span: 16 },
  };
  const onFinish = async (value: any) => {
    const { data } = await request.post('http://101.132.134.186:8080/login', {
      userId: value.username,
      password: value.password
    })
    if (data.status) {
      message.success(data.message)
      console.log(data.data)
      window.localStorage.setItem("userInfo", JSON.stringify(data.data))
      history.push("/home")
    } else {
      message.error(data.message)
    }
  }
  return(
    <Form
      name="normal_login"
      className="login-form"
      initialValues={{ remember: true }}
      onFinish={onFinish}
    >
      <Form.Item
        name="username"
        rules={[{ required: true, message: '请输入用户名!' }]}
      >
        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="用户名" />
      </Form.Item>
      <Form.Item
        name="password"
        rules={[{ required: true, message: '请输入密码!' }]}
      >
        <Input.Password
          prefix={<LockOutlined className="site-form-item-icon" />}
          type="password"
          placeholder="密码"
        />
      </Form.Item>

      <Form.Item >
        <Row justify="space-around">
          <Col>
            <Button type="primary" htmlType="submit" className="login-form-button">
              登录
            </Button>
          </Col>
          <Col>
            <Button type="primary" className="login-form-button" onClick={() => {setLogin(false)}}>
              注册
            </Button>
          </Col>
        </Row>
      </Form.Item>
    </Form>
  )
}

const RegisterBox = (props: Iprops) => {
  let history = useHistory()
  const formItemLayout = {
    labelCol: {
      xs: { span: 24 },
      sm: { span: 6 },
    },
    wrapperCol: {
      xs: { span: 24 },
      sm: { span: 16 },
    },
  };

  const tailFormItemLayout = {
    wrapperCol: {
      xs: {
        span: 24,
        offset: 4,
      },
      sm: {
        span: 24,
        offset: 4,
      },
    },
  };


  const [form] = Form.useForm();

  const onFinish = async (value: any) => {
    console.log(value)
    const { data } = await request.post('http://101.132.134.186:8080/register', {
      nickname: value.nickname,
      password: value.password
    })
    if (data.status) {
      message.success(data.message)
      console.log(data.data)
      window.localStorage.setItem("userInfo", data.data)
      history.push("/home")
      alert(`注意请记住您的账号${data.data}, 用于登录！`)
    } else {
      message.error(data.message)
    }
  }

  const { setLogin } = props
  return (
    <Form
      {...formItemLayout}
      form={form}
      name="register"
      onFinish={onFinish}
      initialValues={{
        prefix: '86',
      }}
      scrollToFirstError
    >
      <Form.Item
        name="nickname"
        label={
          <span>
            昵称&nbsp;
            <Tooltip title="你想要别人称呼你为?">
              <QuestionCircleOutlined />
            </Tooltip>
          </span>
        }
        rules={[{ required: true, message: '请输入昵称!', whitespace: true }]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="password"
        label="密码"
        rules={[
          {
            required: true,
            message: '请输入密码!',
          },
        ]}
        hasFeedback
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        name="confirm"
        label="确认密码"
        dependencies={['password']}
        hasFeedback
        rules={[
          {
            required: true,
            message: '请确认密码!',
          },
          ({ getFieldValue }) => ({
            validator(rule, value) {
              if (!value || getFieldValue('password') === value) {
                return Promise.resolve();
              }
              return Promise.reject('密码不一致!');
            },
          }),
        ]}
      >
        <Input.Password />
      </Form.Item>

      <Form.Item {...tailFormItemLayout}>
        <Row justify="space-around">
          <Col>
            <Button type="primary" htmlType="submit" className="login-form-button">
              注册
            </Button>
          </Col>
          <Col>
            <Button type="primary" className="login-form-button" onClick={() => {setLogin(true)}}>
              去登录
            </Button>
          </Col>
        </Row>
      </Form.Item>
    </Form>
  )
}

const Login: React.FC = () => {
  if (info) {
    let history = useHistory()
    history.push("/home")
  }
  const [isLogin, setLogin] = useState<boolean>(true)

  return (
    <div className="wrapper">
      <div className="container">
        <h1>Welcome</h1>
        { isLogin ? <LoginBox setLogin={setLogin} /> : <RegisterBox setLogin={setLogin} /> }
      </div>
      <Square />
    </div>
  )
}

export default Login;
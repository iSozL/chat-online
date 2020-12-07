import React, { ReactElement, useState } from 'react';
import './index.less'
import { Form, Input, Button, Row, Col, Tooltip, Select, Cascader, AutoComplete, Checkbox } from 'antd';
import { UserOutlined, LockOutlined, QuestionCircleOutlined } from '@ant-design/icons';
const { Option } = Select;
const AutoCompleteOption = AutoComplete.Option;

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
  const { setLogin } = props
  const layout = {
    labelCol: { span: 6 },
    wrapperCol: { span: 16 },
  };
  const tailLayout = {
    wrapperCol: { offset: 6, span: 16 },
  };
  return(
    <Form
      name="normal_login"
      className="login-form"
      initialValues={{ remember: true }}
      // onFinish={onFinish}
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

  const onFinish = (values: any) => {
    console.log('Received values of form: ', values);
  };

  const prefixSelector = (
    <Form.Item name="prefix" noStyle>
      <Select style={{ width: 70 }}>
        <Option value="86">+86</Option>
        <Option value="87">+87</Option>
      </Select>
    </Form.Item>
  );

  const [autoCompleteResult, setAutoCompleteResult] = useState([]);

  const websiteOptions = autoCompleteResult.map(website => ({
    label: website,
    value: website,
  }));


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
        name="email"
        label="邮箱"
        rules={[
          {
            type: 'email',
            message: '请输入有效邮箱',
          },
          {
            required: true,
            message: '请输入邮箱',
          },
        ]}
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
        name="phone"
        label="电话号码"
        rules={[{ required: true, message: '请输入电话号码!' }]}
      >
        <Input addonBefore={prefixSelector} style={{ width: '100%' }} />
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
import React from 'react';
import ReactDOM from 'react-dom';
import {
  HashRouter as Router,
  Switch,
  Route,
  Redirect
} from "react-router-dom";

import Login from './views/login/index';
import Main from './views/main/index'

import wpkReporter from 'wpk-reporter' // 导入基础sdk

const __wpk = new wpkReporter({
  bid: '4hpssjip-n87xxkc0', // 新建应用时确定
  plugins: []
})

__wpk.installAll()

const Routers = [
  { path: "/", name: "home", component: Main },
]
const Index:React.FC = () => {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login} />
        {
          Routers.map((item, index) => {
            return (
              <Route key={index} path={item.path} exact render = { 
                (props: any) =>(window.localStorage.getItem("userInfo") ? <item.component {...props} /> : <Redirect to="/login" push />)
                } 
              />
            )
          })
        }
      </Switch>
    </Router>
  )
}

ReactDOM.render(<Index />, document.getElementById("root"))
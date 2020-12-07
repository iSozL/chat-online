import React from 'react';
import ReactDOM from 'react-dom';
import {
  HashRouter as Router,
  Switch,
  Route
} from "react-router-dom";

import Login from './views/login/index';
import Main from './views/main/index'

const Index:React.FC = () => {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login}></Route>
        <Route path="/" component={Main} exact></Route>
      </Switch>
    </Router>
  )
}

ReactDOM.render(<Index />, document.getElementById("root"))
import React from 'react';
import ReactDOM from 'react-dom';
import {
  HashRouter as Router,
  Switch,
  Route
} from "react-router-dom";

import Login from './views/login/index';

const Index:React.FC = () => {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login} exact></Route>
      </Switch>
    </Router>
  )
}

ReactDOM.render(<Index />, document.getElementById("root"))
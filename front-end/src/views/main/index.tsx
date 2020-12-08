import * as React from 'react';
import './index.scss'
import FriendContent from './friendContent/index';
import Message from './message/index'
import { Container } from './store/index'


const Main: React.FC = () => {
  return (
    <div className="main">
      <div className="main-container">
        <div className="aside">
          <div className="avater">
            <img src={require('../../assets/imgs/avater.svg')} />
          </div>
          <div>
            <img className="me" src={require('../../assets/imgs/me.svg')} />
          </div>
        </div>
        <div className="main-body">
          <Container>
            <FriendContent />
            <Message />
          </Container>
        </div>
      </div>
    </div>
  )
}

export default Main
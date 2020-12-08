import React from 'react';
import { Input } from 'antd';
const { Search } = Input
import './index.scss'

const AddFriend = () => {
  const onSearch = value => console.log(value)

  return (
    <div className="search-container">
      <Search placeholder="请输入查找的昵称" onSearch={onSearch} style={{width: 600}} />
      <img style={{width: "450px", marginTop: "60px"}} src={require('../../../assets/imgs/friend.png')} />
    </div>
  )
}

export default AddFriend
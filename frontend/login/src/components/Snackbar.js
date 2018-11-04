import React from 'react';
import '../App.css';

const Snackbar = props => {
  let { show, cor, msg } = props;
  return <div id="snackbar" className={ show } style={ { backgroundColor: cor } } >{ msg }</div>
}

export default Snackbar;

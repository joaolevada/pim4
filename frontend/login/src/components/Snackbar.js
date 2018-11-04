import React, { Component } from 'react';
import '../App.css';

class Snackbar extends Component {
  render() {
    return (
        <div id="snackbar" className={this.props.show} style={{backgroundColor: this.props.cor}} >{this.props.msg}</div>
    );
  }
}

export default Snackbar;

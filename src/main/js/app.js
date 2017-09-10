import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery';

function getHighlightedText(text, higlight) {
  // Split text on higlight term, include term itself into parts, ignore case
  var parts = text.split(new RegExp(`(${higlight})`, 'gi'));
  return <span>{parts.map(part => part.toLowerCase() === higlight.toLowerCase() ? <mark>{part}</mark> : part)}</span>
}

const Listing = (listing) => {

return (
    <div>
    <table >
    <tbody>
    <tr>
      <td>
      <b>{getHighlightedText(listing.data.names.join(), listing.term)}</b>
      </td>
    </tr>
    <tr>
      <td>
      {getHighlightedText(listing.data.address, listing.term)}
      </td>
    </tr>
    <tr>
      <td>
       {getHighlightedText(listing.data.city, listing.term)}
       </td>
    </tr>
    <tr>
      <td>
        {getHighlightedText(listing.data.state, listing.term)}
       </td>
      </tr>
    </tbody>
    </table>
    </div>
  )
}

const Listings = (props) => {
  const listings = props.listings.map((listing) => {
    return <Listing key={listing.id} data={listing} term={props.term}/>
  });

  const style = {
    display: "inline-block",
    margin: '0.5em',
    paddingLeft: 3,
    margin: 20,
    listStyle: 'none'
  };

  return (
    <div style={style}>{listings}</div>
  );
};


class SearchBar extends React.Component {
  constructor() {
      super();
      this.state = { term: '' }
    }

  onInputChange(term) {
        this.setState({term});
        this.props.onTermChange(term);
  }

  render() {
    const style = {
      width: 100,
      height: 60,
      padding: 5,
      size: 5,
      border: '2px black'
    }

    return (
        <div>
          <input onChange={event => this.onInputChange(event.target.value)} />
        </div>
    );
  }
}

class App extends React.Component {
  constructor() {
    super();
    this.state = {
        listings: [],
        term: ''
    };
  }

  handleTermChange(term) {
    let _this = this;
    if (term.length > 0)
    {
      $.ajax({
        url: "http://localhost:8080/search?query=" + encodeURIComponent(term)
      }).then(function (data) {
        _this.setState({
          listings: data,
          term: term
        });
      });
    }
    else {
      _this.setState({
        listings: [],
        term: term
      });
    }
  }

  render() {
    return (
      <div>
        <SearchBar onTermChange={term => this.handleTermChange(term)} />
        <Listings listings={this.state.listings}  term={this.state.term}/>
      </div>
    );
  }
}

ReactDOM.render(
  <App />,
  document.getElementById('react')
);


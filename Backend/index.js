// For development/testing purposes
"use strict";

var config = require('./config.json');
var _ = require('lodash');
var loremIpsum = require('lorem-ipsum');

var aws = require('aws-sdk');
aws.config.update({
  region: config.region,
  accessKeyId: config.accessKeyId,
  secretAccessKey: config.secretAccessKey
});
aws.config.setPromisesDependency(require('q').Promise);

exports.handler = function( event, context ) {

  var providersJSON = {
    "providers": []
  };
  var messagesJSON = {
    "messages": []
  };
  var newsJSON = {
    "news": []
  };

  var dummyInfo = loremIpsum({count: 3, units: 'paragraphs', format: 'plain'});

  var providersNames = [
    'Banco Mercantil',
    'Cantv',
    'Movistar de Venezuela',
    'GMVV',
    'LaIguana',
    'Banco Provincial',
    'Televen',
    'Ministerio de Ambiente',
    'Globovision',
    'Empresas Polar'
  ];

  var getAMessageJSON = function(id, authorName, type){
    return {
      "id": id,
      "title": type + " " + id,
      "body": type + " " + id + "\n\n" + dummyInfo,
      "date": new Date(),
      "name": authorName,
      "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_"+ authorName.replace(/ /g,'') +".jpg",
      "type": type.toLowerCase()
    };
  };

  var addANews = function (authorName) {
    var id = newsJSON.news.length + 1;
    var news = getAMessageJSON(id, authorName, 'News');

    newsJSON.news.push(news);
  };
  var addAMessage = function (authorName) {
    var id = messagesJSON.messages.length + 1;
    var message = getAMessageJSON(id, authorName, 'Message');

    messagesJSON.messages.push(message);
  };

  var addAProvider = function (name) {
    var provider = {
      "id": providersJSON.providers.length + 1,
      "name": name,
      "info": name + " Info\n\n" + dummyInfo,
      "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_"+ name.replace(/\s/g,'') +".jpg"
    };
    providersJSON.providers.push(provider);
  };

  var getAProviderJSON = function(id){
    var providerInfo = _.filter(providersJSON.providers, _.matches({ 'id': parseInt(id)}));
    var news = [];
    var messages = [];

    _.times(5, function(){
      var messageId = news.length + 1;
      news.push(getAMessageJSON(messageId, providersNames[id-1], 'News'));
    });

    _.times(2, function(){
      var messageId = messages.length + 1;
      messages.push(getAMessageJSON(messageId, providersNames[id-1], 'Message'));
    });

    return {
      "provider": providerInfo,
      "news": news,
      "messages": messages
    };
  };

  _.forEach(providersNames, function(name){
    addAProvider(name);
  });

  _.times(5, function(){
    addAMessage(_.sample(providersNames));
  });

  _.times(30, function(){
    addANews(_.sample(providersNames));
  });

  var resource_path = event.context['resource-path'];
  var id;

  switch (resource_path){
    case "/news":
      console.log(newsJSON);
      context.done(null, newsJSON);
      break;
    case "/news/{id}":
      id = event.params.path.id;
      context.done(null, { "news": _.filter(newsJSON.news, _.matches({ 'id': parseInt(id)}))});
      break;
    case "/providers/{id}":
      id = event.params.path.id;
      console.log(getAProviderJSON(id));
      context.done(null, getAProviderJSON(id));
      break;
    case "/providers":
      console.log(providersJSON);
      context.done(null, providersJSON);
      break;
    case "/messages/{id}":
      id = event.params.path.id;
      context.done(null, { "message": _.filter(providersJSON.providers, _.matches({ 'id': parseInt(id)}))});
      break;
    case "/messages":
      console.log(messagesJSON);
      context.done(null, messagesJSON);
      break;
    default:
      context.done(null, event);
      break;
  }
};

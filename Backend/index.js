// For development/testing purposes
"use strict";

var config = require('./config.json');
var _ = require('lodash');
var Q = require('q');

var aws = require('aws-sdk');
aws.config.update({
  region: config.region,
  accessKeyId: config.accessKeyId,
  secretAccessKey: config.secretAccessKey
});
aws.config.setPromisesDependency(require('q').Promise);

exports.handler = function( event, context ) {

  var dummyInfo  =
    "Cras mollis ipsum nec leo finibus, non pulvinar nisl scelerisque. Sed mi quam, facilisis" +
    "quis ipsum ut, placerat interdum risus. Nullam mattis condimentum diam vel lacinia. Cum soci" +
    "is natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla consequa" +
    "t ligula eu suscipit scelerisque. Donec semper neque ac nibh consequat facilisis. Duis maxim" +
    "us ullamcorper feugiat. Nam accumsan arcu eget magna iaculis, eu porta velit varius. Ut bibe" +
    "ndum nunc ut nisi ullamcorper dignissim." + "\n\n" +

    "Aliquam non magna est. Fusce vulputate pellentesque massa vitae scelerisque. Phasellus a" +
    "arcu vitae tortor commodo sollicitudin. Interdum et malesuada fames ac ante ipsum primis in" +
    "faucibus. Quisque feugiat nisi sit amet nunc consectetur lobortis. Suspendisse mattis euismo" +
    "d nulla, at egestas ligula sodales non. Donec egestas a diam eget sodales. Suspendisse poten" +
    "ti. In tincidunt nisl et mollis ultrices. Curabitur ultrices tempor luctus." + "\n\n" +

    "Aenean tincidunt vel tellus id laoreet. Aenean ac purus ac sapien molestie aliquet suscipit" +
    "id turpis. Aliquam vitae eleifend ligula. In convallis feugiat scelerisque. Phasellus ut pel" +
    "lentesque sapien. Ut sollicitudin neque purus, at convallis quam consequat sit amet. Vivamus" +
    "cursus nisi at luctus pellentesque.";

  var messages = {
    "messages": [
      {
        "id": 1,
        "title": "News 1",
        "body": "News 1 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "LaIguana",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_LaIguana.jpg",
        "type": "message"

      },
      {
        "id": 2,
        "title": "News 2",
        "body": "News 2 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Banco Mercantil",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg",
        "type": "message"

      },
      {
        "id": 3,
        "title": "News 3",
        "body": "News 3 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "GMVV",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_GMVV.jpg",
        "type": "message"

      },
      {
        "id": 4,
        "title": "News 4",
        "body": "News 4 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Cantv",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Cantv.jpg",
        "type": "message"

      }
    ]
  };

  var news = {
    "news": [
      {
        "id": 1,
        "title": "News 1",
        "body": "News 1 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "LaIguana",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_LaIguana.jpg",
        "type": "news"

      },
      {
        "id": 2,
        "title": "News 2",
        "body": "News 2 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Banco Mercantil",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg",
        "type": "news"

      },
      {
        "id": 3,
        "title": "News 3",
        "body": "News 3 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "GMVV",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_GMVV.jpg",
        "type": "news"

      },
      {
        "id": 4,
        "title": "News 4",
        "body": "News 4 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Cantv",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Cantv.jpg",
        "type": "news"

      },
      {
        "id": 5,
        "title": "News 5",
        "body": "News 5 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Banco Mercantil",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg",
        "type": "news"

      },
      {
        "id": 6,
        "title": "News 6",
        "body": "News 6 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Movistar de Venezuela",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Movistar.jpg",
        "type": "news"

      },
      {
        "id": 7,
        "title": "News 7",
        "body": "News 7 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "GMVV",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_GMVV.jpg",
        "type": "news"

      },
      {
        "id": 8,
        "title": "News 8",
        "body": "News 8 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "LaIguana",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_LaIguana.jpg",
        "type": "news"

      },
      {
        "id": 9,
        "title": "News 9",
        "body": "News 9 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Cantv",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Cantv.jpg",
        "type": "news"

      },
      {
        "id": 10,
        "title": "News 10",
        "body": "News 10 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Mercantil",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg",
        "type": "news"

      },
      {
        "id": 11,
        "title": "News 11",
        "body": "News 11 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Mercantil",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg",
        "type": "news"

      },
      {
        "id": 12,
        "title": "News 12",
        "body": "News 12 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Mercantil",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg",
        "type": "news"

      },
      {
        "id": 13,
        "title": "News 13",
        "body": "News 13 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Mercantil",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg",
        "type": "news"

      },
      {
        "id": 14,
        "title": "News 14",
        "body": "News 14 \n\n" + dummyInfo,
        "date": new Date(),
        "name": "Mercantil",
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg",
        "type": "news"

      }
    ]
  };

  var providers = {
    "providers": [
      {
        "id": 1,
        "name": "Banco Mercantil",
        "info": "Banco Mercantil Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg"
      },
      {
        "id": 2,
        "name": "Cantv",
        "info": "Cantv Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Cantv.jpg"
      },
      {
        "id": 3,
        "name": "Movistar de Venezuela",
        "info": "Movistar de Venezuela Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Movistar.jpg"
      },
      {
        "id": 4,
        "name": "GMVV",
        "info": "GMVV Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_GMVV.jpg"
      },
      {
        "id": 5,
        "name": "LaIguana.TV",
        "info": "LaIguana.TV Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_LaIguana.jpg"
      },
      {
        "id": 6,
        "name": "Banco Provincial",
        "info": "Banco Provincial Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Provincial.jpg"
      },
      {
        "id": 7,
        "name": "Televen",
        "info": "Televen Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Televen.jpg"
      },
      {
        "id": 8,
        "name": "Ministerio de Ambiente",
        "info": "Ministerio de Ambiente Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_MinAmbiente.jpg"
      },
      {
        "id": 9,
        "name": "Globovision",
        "info": "Globovision Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Globovision.jpg"
      },
      {
        "id": 10,
        "name": "Empresas Polar",
        "info": "Empresas Polar Info\n\n" + dummyInfo,
        "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_EmpresasPolar.jpg"
      }
    ]
  };

  var resource_path = event.context['resource-path'];

  switch (resource_path){
    case "/news":
      console.log(news);
      context.done(null, news);
      break;
    case "/news/{id}":
      var id = event.params.path.id;
      context.done(null, { "news": _.filter(news.news, _.matches({ 'id': parseInt(id)}))});
      break;
    case "/providers/{id}":
      var id = event.params.path.id;
      context.done(null, { "provider": _.filter(providers.providers, _.matches({ 'id': parseInt(id)}))})
      break;
    case "/providers":
      console.log(providers);
      context.done(null, providers);
      break;
    case "/messages/{id}":
      var id = event.params.path.id;
      context.done(null, { "message": _.filter(providers.providers, _.matches({ 'id': parseInt(id)}))})
      break;
    case "/messages":
      console.log(messages);
      context.done(null, messages);
      break;
    default:
          context.done(null, event);
          break;
  }
};

// For development/testing purposes
"use strict";

var config = require('./config.json');
var _ = require('lodash');
var Q = require('q');
var crypto = require('crypto');
var path = require('path');
var ejs = require('ejs'),
    fs = require('fs');

var aws = require('aws-sdk');
aws.config.update({
  region: config.region,
  accessKeyId: config.accessKeyId,
  secretAccessKey: config.secretAccessKey
});
aws.config.setPromisesDependency(require('q').Promise);
var ses = new aws.SES({apiVersion: '2010-12-01'});

var docClient = new aws.DynamoDB.DocumentClient();

exports.handler = function( event, context ) {

  var dummyInfo  =
    "Cras mollis ipsum nec leo finibus, non pulvinar nisl scelerisque. Sed mi quam, facilisis" +
    "quis ipsum ut, placerat interdum risus. Nullam mattis condimentum diam vel lacinia. Cum soci" +
    "is natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla consequa" +
    "t ligula eu suscipit scelerisque. Donec semper neque ac nibh consequat facilisis. Duis maxim" +
    "us ullamcorper feugiat. Nam accumsan arcu eget magna iaculis, eu porta velit varius. Ut bibe" +
    "ndum nunc ut nisi ullamcorper dignissim." + "\n" +

    "Aliquam non magna est. Fusce vulputate pellentesque massa vitae scelerisque. Phasellus a" +
    "arcu vitae tortor commodo sollicitudin. Interdum et malesuada fames ac ante ipsum primis in" +
    "faucibus. Quisque feugiat nisi sit amet nunc consectetur lobortis. Suspendisse mattis euismo" +
    "d nulla, at egestas ligula sodales non. Donec egestas a diam eget sodales. Suspendisse poten" +
    "ti. In tincidunt nisl et mollis ultrices. Curabitur ultrices tempor luctus." + "\n" +

    "In in lorem et mauris laoreet vestibulum. Phasellus at aliquet ipsum, eget efficitur lectus." +
    "Phasellus vitae turpis dapibus, pulvinar justo non, congue eros. Curabitur nisl ante, lobort" +
    "is et nunc ut, auctor ultrices lacus. Duis id sapien et justo rhoncus sagittis. Mauris biben" +
    "dum nibh eleifend, lacinia nunc in, lobortis nisl. Morbi iaculis fringilla nulla id porttito" +
    "r. Vivamus at suscipit nisi. Sed porttitor turpis sed nibh cursus vehicula. Etiam feugiat le" +
    "o vel diam volutpat, id mattis lectus pulvinar. Pellentesque euismod risus eget urna cursus," +
    "nec scelerisque mi iaculis. Nunc consequat nunc justo, quis ullamcorper nisl efficitur et." + "\n" +

    "Integer sed augue ornare, maximus mi eget, mattis sem. Ut magna sem, vulputate a turpis in," +
    "blandit vestibulum mauris. Donec erat dui, bibendum in bibendum auctor, aliquet id libero. V" +
    "ivamus feugiat erat lorem, nec auctor enim interdum vitae. Etiam at nisi faucibus, pulvinar" +
    "ante vitae, finibus nunc. Nulla egestas, urna at pharetra aliquet, sapien tellus tristique e" +
    "nim, pharetra aliquet massa arcu in tortor. Nam sit amet vehicula elit. Vestibulum vehicula" +
    "eget lacus sodales porttitor. Fusce hendrerit metus et luctus maximus. Curabitur viverra qua" +
    "m nec libero placerat blandit." + "\n" +

    "Curabitur ligula eros, rutrum in diam sed, vulputate porta mi. Praesent scelerisque blandit" +
    "leo, in varius est imperdiet pulvinar. Vivamus lorem diam, aliquam quis venenatis quis, vest" +
    "ibulum ac est. Maecenas fermentum elementum metus at fringilla. Nunc convallis felis et nunc" +
    "suscipit, ac tempus augue sagittis. Fusce nisl lectus, aliquet a sem vel, dapibus efficitur" +
    "ante. Suspendisse non ante non ex condimentum elementum. Vestibulum ultrices condimentum est" +
    ", in ornare ante vestibulum in. Suspendisse eu dolor ipsum. Vestibulum vitae sem justo. Duis" +
    "et varius nisl, ut gravida augue. Ut nec sollicitudin lacus. Aliquam blandit dictum elit sed" +
    "euismod." + "\n" +

    "Maecenas eu porta felis. Nullam aliquet eget risus et tristique. In ac magna et leo mattis" +
    "convallis. Maecenas eget mauris suscipit, faucibus diam non, porta tellus. Suspendisse porta" +
    "lacinia nisi sit amet consectetur. Duis condimentum mi quis auctor varius. Nunc faucibus lac" +
    "us ac nunc dictum, ac pretium magna pellentesque. Proin felis leo, ultricies vitae tellus eu" +
    ", lacinia ultrices eros. Nulla et facilisis purus. Vivamus dictum laoreet felis, sit amet fr" +
    "ingilla sapien pharetra id. Pellentesque eget dictum ipsum. Phasellus pellentesque facilisis" +
    "arcu ac finibus. Etiam ut tincidunt ligula. Duis at aliquet orci, ut accumsan urna. Nullam" +
    "et iaculis est." + "\n" +

    "Donec egestas egestas feugiat. Curabitur tempor velit erat. Duis ac mauris ac magna maximus" +
    "pulvinar. Phasellus sagittis felis at blandit fermentum. Sed elementum id erat ut efficitur." +
    "Sed hendrerit ante id quam tincidunt fermentum. Nam vehicula lacus viverra placerat mollis." +
    "Quisque ornare nisi vel ex ullamcorper sollicitudin. Sed non finibus enim. Mauris at dolor l" +
    "igula. Quisque vel sem malesuada, facilisis nisi et, consequat felis. Vestibulum vel tristiq" +
    "ue risus, nec efficitur orci. Aliquam varius lacus erat, non pellentesque ante elementum a." +
    "In hac habitasse platea dictumst. Aenean eu elementum odio." + "\n" +

    "Donec ut venenatis elit. Curabitur laoreet, ex id consectetur mollis, nibh mauris elementum" +
    "lectus, ac dapibus nunc risus eu eros. Donec vitae commodo lorem. Sed dui sapien, facilisis" +
    "vulputate felis non, dignissim molestie nunc. Sed leo risus, facilisis eget ultrices quis, d" +
    "apibus id eros. Aliquam erat volutpat. Fusce feugiat nunc sed nibh cursus gravida. Vestibulu" +
    "m at sagittis tortor. Proin tempus, elit a molestie aliquam, mauris mauris convallis massa," +
    "at blandit nisi ante at ligula. Aliquam mauris leo, faucibus ac blandit quis, pretium sit am" +
    "et purus. Fusce commodo dui vel pellentesque interdum. Aliquam mollis massa ac ante egestas" +
    "luctus." + "\n" +

    "Quisque nec luctus odio. Ut gravida dui quis arcu tincidunt, quis aliquet ex iaculis." +
    "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Inte" +
    "ger ultrices erat eget odio mattis vehicula. Sed a lorem urna. Phasellus auctor ornare vehic" +
    "ula. Ut sed pulvinar urna." + "\n" +

    "Aenean tincidunt vel tellus id laoreet. Aenean ac purus ac sapien molestie aliquet suscipit" +
    "id turpis. Aliquam vitae eleifend ligula. In convallis feugiat scelerisque. Phasellus ut pel" +
    "lentesque sapien. Ut sollicitudin neque purus, at convallis quam consequat sit amet. Vivamus" +
    "cursus nisi at luctus pellentesque.";

  var response = {
    "providers": [
      { "id": 1,  "name": "Banco Mercantil", "info": "Banco Mercantil Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Mercantil.jpg" },
      { "id": 2,  "name": "Cantv", "info": "Cantv Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Cantv.jpg" },
      { "id": 3,  "name": "Movistar de Venezuela", "info": "Movistar de Venezuela Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Movistar.jpg" },
      { "id": 4,  "name": "GMVV", "info": "GMVV Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_GMVV.jpg" },
      { "id": 5,  "name": "LaIguana.TV", "info": "LaIguana.TV Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_LaIguana.jpg" },
      { "id": 6,  "name": "Banco Provincial", "info": "Banco Provincial Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Provincial.jpg" },
      { "id": 7,  "name": "Televen", "info": "Televen Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Televen.jpg" },
      { "id": 8,  "name": "Ministerio de Ambiente", "info": "Ministerio de Ambiente Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_MinAmbiente.jpg" },
      { "id": 9,  "name": "Globovision", "info": "Globovision Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_Globovision.jpg" },
      { "id": 10, "name": "Empresas Polar", "info": "Empresas Polar Info" + dummyInfo, "avatar": "https://s3.amazonaws.com/contacta/avatarsContacta_EmpresasPolar.jpg" }
    ]
  };

  context.done(null, response);
};
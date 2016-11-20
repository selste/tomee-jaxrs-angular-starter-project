'use strict';

/* Controllers */

var phonecatApp = angular.module('phonecatApp', []);

phonecatApp.controller('PhoneListCtrl', function($scope, $http, $log) {
  $http.get('gallery/phones').success(function(data) {
    $log.debug(data)

    $scope.phones = data;
  });

  $http.get('gallery/carriers').success(function(data) {
    $log.debug(data)

    $scope.carriers = data;
  });

  $scope.orderProp = 'age';

  $scope.phoneForm = {}

  $scope.phoneForm.submitTheForm = function(item, event) {
       var dataObject = {
          name: $scope.phoneForm.name,
          age: $scope.phoneForm.age,
          carrier: $scope.phoneForm.carrier,
          snippet: $scope.phoneForm.snippet
       };

       var responsePromise = $http.post("gallery/save", dataObject, {});
       responsePromise.success(function(dataFromServer, status, headers, config) {
          console.log(dataFromServer.title);
       });

       responsePromise.error(function(data, status, headers, config) {
          alert("Submitting form failed!");
       });
  }
});
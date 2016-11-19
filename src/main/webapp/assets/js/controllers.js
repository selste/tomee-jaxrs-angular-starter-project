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

  $scope.phoneForm = {};
});
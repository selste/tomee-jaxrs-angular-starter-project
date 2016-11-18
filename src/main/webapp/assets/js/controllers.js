'use strict';

/* Controllers */

var phonecatApp = angular.module('phonecatApp', []);

phonecatApp.controller('PhoneListCtrl', function($scope, $http, $log) {
  $http.get('phone/list').success(function(data) {
    // $log.debug(data)

    $scope.phones = data;
  });

  $scope.orderProp = 'age';
});
(function () {
    'use strict';
    //IIFE


    function MyAddressController($scope, $window) {
        var ctrl = this;
    }

    angular.module('myContactApp').component('myAddress', {
        templateUrl: 'components/manageContacts/myAddress.html?' + new Date(),
        controller: MyAddressController,
        bindings: {
            address: '<',
            displayMode: '<'
        }
    });


    //END IIFE
})();
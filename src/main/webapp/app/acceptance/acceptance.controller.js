(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('acceptanceController', acceptanceController);

    acceptanceController.$inject = ['$state'];

    function acceptanceController($state) {
        var vm = this;
       };
})();

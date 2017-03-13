(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emmision_pollution_controllController', Emmision_pollution_controllController);

    Emmision_pollution_controllController.$inject = ['Emmision_pollution_controll'];

    function Emmision_pollution_controllController(Emmision_pollution_controll) {

        var vm = this;

        vm.emmision_pollution_controlls = [];

        loadAll();

        function loadAll() {
            Emmision_pollution_controll.query(function(result) {
                vm.emmision_pollution_controlls = result;
                vm.searchQuery = null;
            });
        }
    }
})();

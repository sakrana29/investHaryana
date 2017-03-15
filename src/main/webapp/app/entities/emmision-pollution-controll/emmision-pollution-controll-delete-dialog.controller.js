(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emmision_pollution_controllDeleteController',Emmision_pollution_controllDeleteController);

    Emmision_pollution_controllDeleteController.$inject = ['$uibModalInstance', 'entity', 'Emmision_pollution_controll'];

    function Emmision_pollution_controllDeleteController($uibModalInstance, entity, Emmision_pollution_controll) {
        var vm = this;

        vm.emmision_pollution_controll = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Emmision_pollution_controll.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

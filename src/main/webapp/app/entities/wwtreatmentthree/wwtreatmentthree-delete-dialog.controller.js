(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmentthreeDeleteController',WwtreatmentthreeDeleteController);

    WwtreatmentthreeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Wwtreatmentthree'];

    function WwtreatmentthreeDeleteController($uibModalInstance, entity, Wwtreatmentthree) {
        var vm = this;

        vm.wwtreatmentthree = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Wwtreatmentthree.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

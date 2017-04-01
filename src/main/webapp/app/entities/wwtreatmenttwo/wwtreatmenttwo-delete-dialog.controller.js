(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmenttwoDeleteController',WwtreatmenttwoDeleteController);

    WwtreatmenttwoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Wwtreatmenttwo'];

    function WwtreatmenttwoDeleteController($uibModalInstance, entity, Wwtreatmenttwo) {
        var vm = this;

        vm.wwtreatmenttwo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Wwtreatmenttwo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

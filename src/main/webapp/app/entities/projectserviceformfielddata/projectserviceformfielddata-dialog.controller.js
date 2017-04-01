(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectserviceformfielddataDialogController', ProjectserviceformfielddataDialogController);

    ProjectserviceformfielddataDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectserviceformfielddata'];

    function ProjectserviceformfielddataDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectserviceformfielddata) {
        var vm = this;

        vm.projectserviceformfielddata = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.projectserviceformfielddata.id !== null) {
                Projectserviceformfielddata.update(vm.projectserviceformfielddata, onSaveSuccess, onSaveError);
            } else {
                Projectserviceformfielddata.save(vm.projectserviceformfielddata, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectserviceformfielddataUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

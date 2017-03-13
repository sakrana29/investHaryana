(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impactdetailDialogController', Environment_impactdetailDialogController);

    Environment_impactdetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Environment_impactdetail'];

    function Environment_impactdetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Environment_impactdetail) {
        var vm = this;

        vm.environment_impactdetail = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.environment_impactdetail.id !== null) {
                Environment_impactdetail.update(vm.environment_impactdetail, onSaveSuccess, onSaveError);
            } else {
                Environment_impactdetail.save(vm.environment_impactdetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:environment_impactdetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setDocument_attached = function ($file, environment_impactdetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        environment_impactdetail.document_attached = base64Data;
                        environment_impactdetail.document_attachedContentType = $file.type;
                    });
                });
            }
        };

    }
})();

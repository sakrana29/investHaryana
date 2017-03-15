(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingdetailDialogController', ManufacturingdetailDialogController);

    ManufacturingdetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Manufacturingdetail'];

    function ManufacturingdetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Manufacturingdetail) {
        var vm = this;

        vm.manufacturingdetail = entity;
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
            if (vm.manufacturingdetail.id !== null) {
                Manufacturingdetail.update(vm.manufacturingdetail, onSaveSuccess, onSaveError);
            } else {
                Manufacturingdetail.save(vm.manufacturingdetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:manufacturingdetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setManufacturing_flow_document = function ($file, manufacturingdetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        manufacturingdetail.manufacturing_flow_document = base64Data;
                        manufacturingdetail.manufacturing_flow_documentContentType = $file.type;
                    });
                });
            }
        };

    }
})();

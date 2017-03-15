(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ElectricrequirementDialogController', ElectricrequirementDialogController);

    ElectricrequirementDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Electricrequirement'];

    function ElectricrequirementDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Electricrequirement) {
        var vm = this;

        vm.electricrequirement = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
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
            if (vm.electricrequirement.id !== null) {
                Electricrequirement.update(vm.electricrequirement, onSaveSuccess, onSaveError);
            } else {
                Electricrequirement.save(vm.electricrequirement, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:electricrequirementUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setTemporaryconnection = function ($file, electricrequirement) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        electricrequirement.temporaryconnection = base64Data;
                        electricrequirement.temporaryconnectionContentType = $file.type;
                    });
                });
            }
        };
        vm.datePickerOpenStatus.temp_load_demand_date = false;

        vm.setRegular_connection_doc = function ($file, electricrequirement) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        electricrequirement.regular_connection_doc = base64Data;
                        electricrequirement.regular_connection_docContentType = $file.type;
                    });
                });
            }
        };
        vm.datePickerOpenStatus.regular_load_demand_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();

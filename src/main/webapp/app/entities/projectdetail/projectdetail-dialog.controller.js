(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailDialogController', ProjectdetailDialogController);

    ProjectdetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Projectdetail'];

    function ProjectdetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Projectdetail) {
        var vm = this;

        vm.projectdetail = entity;
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
            if (vm.projectdetail.id !== null) {
                Projectdetail.update(vm.projectdetail, onSaveSuccess, onSaveError);
            } else {
                Projectdetail.save(vm.projectdetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectdetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setDetail_project_report = function ($file, projectdetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectdetail.detail_project_report = base64Data;
                        projectdetail.detail_project_reportContentType = $file.type;
                    });
                });
            }
        };

        vm.setApproval_document = function ($file, projectdetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectdetail.approval_document = base64Data;
                        projectdetail.approval_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setEdc_sif_clu_fee_paid_document = function ($file, projectdetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectdetail.edc_sif_clu_fee_paid_document = base64Data;
                        projectdetail.edc_sif_clu_fee_paid_documentContentType = $file.type;
                    });
                });
            }
        };

    }
})();

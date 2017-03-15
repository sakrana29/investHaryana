(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectsitedetailDialogController', ProjectsitedetailDialogController);

    ProjectsitedetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Projectsitedetail'];

    function ProjectsitedetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Projectsitedetail) {
        var vm = this;

        vm.projectsitedetail = entity;
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
            if (vm.projectsitedetail.id !== null) {
                Projectsitedetail.update(vm.projectsitedetail, onSaveSuccess, onSaveError);
            } else {
                Projectsitedetail.save(vm.projectsitedetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectsitedetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setKhasra_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.khasra_document = base64Data;
                        projectsitedetail.khasra_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setRevenu_shajra_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.revenu_shajra_document = base64Data;
                        projectsitedetail.revenu_shajra_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setJamabandi = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.jamabandi = base64Data;
                        projectsitedetail.jamabandiContentType = $file.type;
                    });
                });
            }
        };

        vm.setNonencumbrance_certificate = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.nonencumbrance_certificate = base64Data;
                        projectsitedetail.nonencumbrance_certificateContentType = $file.type;
                    });
                });
            }
        };

        vm.setOwnership_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.ownership_document = base64Data;
                        projectsitedetail.ownership_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setLease_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.lease_document = base64Data;
                        projectsitedetail.lease_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setLandagreement_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.landagreement_document = base64Data;
                        projectsitedetail.landagreement_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setSitelayoutplan = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.sitelayoutplan = base64Data;
                        projectsitedetail.sitelayoutplanContentType = $file.type;
                    });
                });
            }
        };

        vm.setLocationplan = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.locationplan = base64Data;
                        projectsitedetail.locationplanContentType = $file.type;
                    });
                });
            }
        };

        vm.setLinearstripplan = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.linearstripplan = base64Data;
                        projectsitedetail.linearstripplanContentType = $file.type;
                    });
                });
            }
        };

        vm.setSitesituated_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.sitesituated_document = base64Data;
                        projectsitedetail.sitesituated_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setBuildingplan_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.buildingplan_document = base64Data;
                        projectsitedetail.buildingplan_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setControlledarea_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.controlledarea_document = base64Data;
                        projectsitedetail.controlledarea_documentContentType = $file.type;
                    });
                });
            }
        };

    }
})();

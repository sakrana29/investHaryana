(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('assignServiceDialogController', assignServiceDialogController);

    assignServiceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'projectservicedetail', 'projectAttachemnt', 'ProjectServiceLog', 'ProjectAttachemnt', 'FileManagement', 'Projectservicedetail'];
    function assignServiceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, projectservicedetail, projectAttachemnt, ProjectServiceLog, ProjectAttachemnt, FileManagement, Projectservicedetail) {
        var vm = this;
        vm.projectservicedetail = projectservicedetail;
        vm.projectServiceLog = ProjectServiceLog;
        vm.projectAttachemnt = projectAttachemnt;
        vm.clear = clear;
        vm.saveAll = saveAll;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function saveAll(){
           updateProjectServiceDetail();
           saveComments();
           saveFile();
           saveFileRecord();

        }

        function updateProjectServiceDetail(){
        alert('updateProjectServiceDetail');
        }

        function saveComments(){
        alert('saveComments');

        }

        function saveFile(){
        alert('saveFile');

        }

        function saveFileRecord(){
        alert('saveFileRecord');
        }
    }
})();

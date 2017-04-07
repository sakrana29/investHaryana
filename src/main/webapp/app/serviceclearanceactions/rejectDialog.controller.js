(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('rejectDialogController', rejectDialogController);

    rejectDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'projectAttachemnt', 'ProjectServiceLog', 'ProjectAttachemnt', 'FileManagement'];
    function rejectDialogController ($timeout, $scope, $stateParams, $uibModalInstance, projectAttachemnt, ProjectServiceLog, ProjectAttachemnt, FileManagement) {
        var vm = this;
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
        alert('saveComments');
           saveComments();
           saveFile();
           saveFileRecord();

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

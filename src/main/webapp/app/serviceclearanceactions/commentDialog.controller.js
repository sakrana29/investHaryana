(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('commentDialogController', commentDialogController);

    commentDialogController.$inject = ['$timeout','$scope','$stateParams','$uibModalInstance','projectAttachemnt','projectServiceLog','ProjectAttachemnt','ProjectServiceLog','FileManagement','Projectservicedetail'];
    function commentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, projectAttachemnt, projectServiceLog, ProjectAttachemnt, ProjectServiceLog, FileManagement, Projectservicedetail) {
        var vm = this;
        vm.projectServiceLog = projectServiceLog;
        vm.projectAttachemnt = projectAttachemnt;
        vm.projectService = $stateParams.projectService;
        var projectAttachmentResultObject=null;

        vm.clear = clear;
        vm.saveComments = saveComments;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function saveComments(){
            vm.projectServiceLog.projectid=vm.projectService.projectid;
            vm.projectServiceLog.serviceid=vm.projectService.serviceid;
            ProjectServiceLog.save(vm.projectServiceLog,onServiceLogSaveSuccess,onServiceLogSaveError);
        }
        function onServiceLogSaveSuccess(result)
        {
//            alert('servicelogsaved');
            $scope.$emit('investhryApp:projectServiceLogUpdate', result);
            vm.projectService.latestComments=vm.projectServiceLog.comments;
            Projectservicedetail.update(vm.projectService,onUpdateProjectServiceSuccess,onUpdateProjectServiceError);
        }
        function onUpdateProjectServiceSuccess(result)
        {
//            alert('projectservicedetail saved');
            $scope.$emit('investhryApp:projectservicedetailUpdate', result);
            if(angular.isDefined(vm.projectAttachemnt.file)){
                saveProjectAttachment();
            }
            else
            {
                $uibModalInstance.close(result);
                vm.isSaving = false;
            }
        }
        function onUpdateProjectServiceError()
        {
//            alert('project service not saved');
            vm.isSaving = false;
        }
        function onServiceLogSaveError()
        {
//            alert('Servicelog not saved');
            vm.isSaving = false;
        }
        function saveProjectAttachment()
        {
            var file=vm.projectAttachemnt.file;
            vm.projectAttachemnt.fileName=file.name;
            vm.projectAttachemnt.description="Open Comments";
            vm.projectAttachemnt.fileExtension =file.type;
            ProjectAttachemnt.save(vm.projectAttachemnt,onSaveProjectAttachmentSuccess,onSaveProjectAttachmentError);
        }
        function onSaveProjectAttachmentSuccess(result)
        {
            $scope.$emit('investhryApp:projectAttachemntUpdate', result);
            var filename = result.id;
            var file =vm.projectAttachemnt.file;
            FileManagement.saveFile(file,filename);
            projectAttachmentResultObject=result;
            projectAttachmentResultObject.serverFileName=result.id;
            ProjectAttachemnt.update(projectAttachmentResultObject,onUpdateProjectAttachmentSuccess,onUpdateProjectAttachmentError);
        }
        function onSaveProjectAttachmentError()
        {
//            alert('project attachment not saved');
            vm.isSaving = false;
        }

        function onUpdateProjectAttachmentSuccess(result)
        {
//            alert('file attachment updated');
            $uibModalInstance.close(result);
        }
        function onUpdateProjectAttachmentError()
        {
//            alert('file attachment not updated');
            vm.isSaving = false;
        }
    }
})();

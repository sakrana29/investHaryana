(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('servicePaymentController', servicePaymentController);

    servicePaymentController.$inject = ['$timeout','$scope','$stateParams','$uibModalInstance','projectServicePayment','projectAttachemnt','projectServiceLog','ProjectAttachemnt','ProjectServiceLog','FileManagement','Projectservicedetail','ProjectServicePaymentDetails','Payment'];
    function servicePaymentController ($timeout, $scope, $stateParams, $uibModalInstance,projectServicePayment, projectAttachemnt, projectServiceLog, ProjectAttachemnt, ProjectServiceLog, FileManagement, Projectservicedetail, ProjectServicePaymentDetails,Payment) {
        var vm = this;
        vm.projectServiceLog = projectServiceLog;
        vm.projectAttachemnt = projectAttachemnt;
        vm.projectServicePayment=projectServicePayment;
        vm.projectService = $stateParams.projectService;
        vm.projectServicePayment.paymentMade=vm.projectService.serviceFee;
//        vm.paymentparameters = paymentparameters;

        var projectAttachmentResultObject=null;

        vm.clear = clear;
        vm.makePayment = makePayment;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function makePayment(){
            vm.projectServicePayment.projectid=vm.projectService.projectid;
            vm.projectServicePayment.serviceid=vm.projectService.serviceid;
            ProjectServicePaymentDetails.save(vm.projectServicePayment,onServicePaymentSaveSuccess,onServicePaymentSaveError);
        }
        function onServicePaymentSaveSuccess(result)
        {
//            alert('servicelogsaved');
            $scope.$emit('investhryApp:projectServicePaymentDetailsUpdate', result);
            vm.projectServiceLog.projectid=vm.projectService.projectid;
            vm.projectServiceLog.serviceid=vm.projectService.serviceid;
            ProjectServiceLog.save(vm.projectServiceLog,onServiceLogSaveSuccess,onServiceLogSaveError);
        }
        function onServicePaymentSaveError()
        {
//            alert('service payment not saved');
            vm.isSaving = false;
        }

        function onServiceLogSaveSuccess(result)
        {
//            alert('servicelogsaved');
            $scope.$emit('investhryApp:projectServiceLogUpdate', result);
            vm.projectService.latestComments=vm.projectServiceLog.comments
            vm.projectService.isPaymentMade=true;
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
            vm.projectAttachemnt.description="Service Payment Attachment";
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

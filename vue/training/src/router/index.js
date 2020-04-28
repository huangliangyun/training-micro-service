import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import PersionInfo from '@/components/info/PersionInfo'
import Notice from '@/components/history/Notice'
import LimitError from '@/components/error/LimitError'
import LeaveApply from '@/components/leave/LeaveApply'
import LeaveManager from '@/components/leave/LeaveManager'
import Subject from '@/components/training/Subject'
import Subjects from '@/components/training/Subjects'
import SubjectsExamine from '@/components/training/SubjectsExamine'
import Document from '@/components/training/Document'
import WeekSummary from '@/components/training/WeekSummary'

Vue.use(Router)

export default new Router({
  routes: [
    { path: '/', name: 'Login', component: Login},
    { path: '/home', name: 'Home', component: Home,
      children: [
        {path: "/home/info", component: PersionInfo, meta: {requireAuth: true}},
        {path: "/home/leave", component: LeaveApply, meta: {requireAuth: true}},
        {path: "/home/leave/manager", component: LeaveManager, meta: {requireAuth: true}},
        {path: "/home/history/notice", component: Notice, meta: {requireAuth: true}},
        {path: "/home/training/subject", component: Subject, meta: {requireAuth: true}},
        {path: "/home/training/subjects", component: Subjects, meta: {requireAuth: true}},
        {path: "/home/training/subjectExamine", component: SubjectsExamine, meta: {requireAuth: true}},
        {path: "/home/training/document", component: Document, meta: {requireAuth: true}},
        {path: "/home/training/weekSummary", component: WeekSummary, meta: {requireAuth: true}},
      ]
    },
    {path: "/error/limit", component: LimitError, meta: {requireAuth: true}},
  ]
});

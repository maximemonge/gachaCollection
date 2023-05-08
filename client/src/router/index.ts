import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import GachaView from '../views/GachaView.vue'
import CollectionView from '../views/CollectionView.vue'
import ProfilView from '../views/ProfilView.vue'
import AccueilView from '../views/AccueilView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'accueil',
    component: AccueilView
  },
  {
    path: '/gacha',
    name: 'gacha',
    component: GachaView
  },
  {
    path: '/collection',
    name: 'collection',
    component: CollectionView,
    props: true
  },
  {
    path: '/profil',
    name: 'profil',
    component: ProfilView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

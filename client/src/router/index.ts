import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import GachaView from '../views/GachaView.vue'
import CollectionView from '../views/CollectionView.vue'
import ProfilView from '../views/ProfilView.vue'
import ConnexionView from '../views/ConnexionView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'connect',
    component: ConnexionView
  },
  {
    path: '/gacha',
    name: 'gacha',
    component: GachaView
  },
  {
    path: '/collection',
    name: 'collection',
    component: CollectionView
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

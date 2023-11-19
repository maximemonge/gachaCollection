import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createI18n } from 'vue-i18n'
import { en } from './traductions/en_EN'
import { fr } from './traductions/fr_FR'
import { jp } from './traductions/jp_JP'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faDog, faGem } from '@fortawesome/free-solid-svg-icons'

library.add(faDog, faGem)

const i18n = createI18n(
    {
        legacy: false, 
        locale: 'fr',  
        fallbackLocale: 'fr', 
        messages: {fr, en, jp}
    }
)

createApp(App)
.use(router)
.use(i18n)
.component('font-awesome-icon', FontAwesomeIcon)
.mount('#app')
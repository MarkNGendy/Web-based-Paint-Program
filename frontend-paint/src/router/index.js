import { createRouter, createWebHistory } from "vue-router";
import Paint from "../components/Paint.vue";
const routes = [
    {
        path: "/",
        name: "Paint",
        component: Paint
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;

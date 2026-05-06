package com.example.makan_log.navigation

object Routes {
    const val Home = "home"
    const val Auth = "auth/{tab}"

    /** Navigate to auth screen starting on login tab */
    const val AuthLogin    = "auth/login"
    /** Navigate to auth screen starting on register tab */
    const val AuthRegister = "auth/register"
}

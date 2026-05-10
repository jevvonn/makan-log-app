package com.example.makan_log.ui.navigation

object Route {
  const val Home = "home"

  const val Splash = "splash"
  const val Auth = "auth/{tab}"

  const val AuthLogin = "auth/login"

  const val AuthRegister = "auth/register"

  const val Bookmark = "bookmark"

  const val Profile = "profile"

  const val Create = "create"
}

val routesWithBottomBar = setOf(
  Route.Home,
  Route.Bookmark,
  Route.Profile,
)


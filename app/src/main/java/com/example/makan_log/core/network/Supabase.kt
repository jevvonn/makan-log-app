package com.example.makan_log.core.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import com.example.makan_log.BuildConfig
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.FlowType

object Supabase {

  val client = createSupabaseClient(
    supabaseUrl = BuildConfig.SUPABASE_URL,
    supabaseKey = BuildConfig.SUPABASE_PUBLISHABLE_KEY
  ) {
    install(Postgrest)
    install(Auth) {
      flowType = FlowType.PKCE
      scheme = "app"
      host = "supabase.com"
    }
  }
}
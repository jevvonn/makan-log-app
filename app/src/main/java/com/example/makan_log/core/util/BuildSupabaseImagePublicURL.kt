package com.example.makan_log.core.util

import com.example.makan_log.BuildConfig

fun BuildSupabaseImagePublicURL(bucket: String, path: String) =
  "${BuildConfig.SUPABASE_URL}/storage/v1/object/public/${bucket}/${path}"
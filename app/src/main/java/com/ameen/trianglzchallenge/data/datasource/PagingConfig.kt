package com.ameen.trianglzchallenge.data.datasource

import androidx.paging.PagingConfig
import com.ameen.trianglzchallenge.core.util.PAGE_SIZE

val PAGE_CONFIG =
    PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false
    )

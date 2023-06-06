package coil.request

import android.graphics.drawable.Drawable
import coil.ImageLoader
import coil.decode.DataSource
import coil.memory.MemoryCache

/**
 * Represents the result of an executed [ImageRequest].
 *
 * @see ImageLoader.enqueue
 * @see ImageLoader.execute
 */
sealed class ImageResult {
    abstract val drawable: Drawable?
    abstract val request: ImageRequest
}

/**
 * Indicates that the request completed successfully.
 */
data class SuccessResult(
    /**
     * The success drawable.
     */
    override val drawable: Drawable,

    /**
     * The request that was executed to create this result.
     */
    override val request: ImageRequest,

    /**
     * The data source that the image was loaded from.
     */
    val dataSource: DataSource,

    /**
     * The cache key for the image in the memory cache.
     * It is 'null' if the image was not written to the memory cache.
     */
    val memoryCacheKey: MemoryCache.Key? = null,

    /**
     * The cache key for the image in the disk cache.
     * It is 'null' if the image was not written to the disk cache.
     */
    val diskCacheKey: String? = null,

    /**
     * 'true' if the image is sampled (i.e. loaded into memory at less than its original size).
     */
    val isSampled: Boolean = false,

    /**
     * 'true' if [ImageRequest.placeholderMemoryCacheKey] was present in the memory cache.
     */
    val isPlaceholderCached: Boolean = false,
) : ImageResult()

/**
 * Indicates that an error occurred while executing the request.
 */
data class ErrorResult(
    /**
     * The error drawable.
     */
    override val drawable: Drawable?,

    /**
     * The request that was executed to create this result.
     */
    override val request: ImageRequest,

    /**
     * The error that failed the request.
     */
    val throwable: Throwable,
) : ImageResult()

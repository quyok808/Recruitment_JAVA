let slideIndex = 0;
const slides = document.querySelectorAll(".list-images img");
const totalSlides = slides.length;
const slideWidth = slides[0].clientWidth;
const listImages = document.querySelector(".list-images");

function showSlides() {
    listImages.style.transform = `translateX(-${slideIndex * slideWidth}px)`;
    slideIndex++;
    if (slideIndex >= totalSlides) {
        slideIndex = 0;
    }
    setTimeout(showSlides, 3500); // Change image every 2 seconds
}

function plusSlides(n) {
    slideIndex += n;
    if (slideIndex >= totalSlides) {
        slideIndex = 0;
    } else if (slideIndex < 0) {
        slideIndex = totalSlides - 1;
    }
    listImages.style.transform = `translateX(-${slideIndex * slideWidth}px)`;
}

// Add event listeners for buttons
document.querySelector(".btn-left").addEventListener("click", () => plusSlides(-1));
document.querySelector(".btn-right").addEventListener("click", () => plusSlides(1));

document.addEventListener("DOMContentLoaded", function() {
    showSlides(); // Start the slideshow on page load
});

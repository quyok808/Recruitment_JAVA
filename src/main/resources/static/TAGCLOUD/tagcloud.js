class TagCloud {
    constructor(container, texts, options = {}) {
        this.container = typeof container === 'string' ? document.querySelector(container) : container;
        this.texts = texts;
        this.options = options;
        this.radius = options.radius || 150;
        this.dtr = Math.PI / 180;
        this.d = 300;
        this.mcList = [];
        this.active = false;
        this.angleX = this.options.maxSpeed === 'fast' ? 0.01 : this.options.maxSpeed === 'slow' ? 0.003 : 0.005;
        this.angleY = this.options.maxSpeed === 'fast' ? 0.01 : this.options.maxSpeed === 'slow' ? 0.003 : 0.005;
        this.init();
    }

    init() {
        this.container.innerHTML = '';
        this.texts.forEach((text, index) => {
            const link = document.createElement('a');
            link.href = `/${text.toLowerCase().replace(/\s+/g, '-')}`;  // Change this to actual URLs if needed
            link.target = "_blank";
            link.textContent = text;
            this.container.appendChild(link);
            const phi = Math.acos(-1 + (2 * (index + 1) - 1) / this.texts.length);
            const theta = Math.sqrt(this.texts.length * Math.PI) * phi;
            this.mcList.push({
                el: link,
                x: this.radius * Math.cos(theta) * Math.sin(phi),
                y: this.radius * Math.sin(theta) * Math.sin(phi),
                z: this.radius * Math.cos(phi)
            });

            link.addEventListener('mouseover', () => this.pause());
            link.addEventListener('mouseout', () => this.resume());
        });
        this.updatePositions();
        this.animate();
    }

    updatePositions() {
        const sin = Math.sin, cos = Math.cos;
        this.mcList.forEach(item => {
            const rx1 = item.x;
            const ry1 = item.y * cos(this.angleX) + item.z * sin(this.angleX);
            const rz1 = item.z * cos(this.angleX) - item.y * sin(this.angleX);

            const rx2 = rx1 * cos(this.angleY) - rz1 * sin(this.angleY);
            const ry2 = ry1;
            const rz2 = rz1 * cos(this.angleY) + rx1 * sin(this.angleY);

            item.x = rx2;
            item.y = ry2;
            item.z = rz2;

            const scale = this.d / (this.d + rz2);
            const alpha = (rz2 + this.radius) / (2 * this.radius);
            item.el.style.left = `${item.x * scale + this.container.offsetWidth / 2 - item.el.offsetWidth / 2}px`;
            item.el.style.top = `${item.y * scale + this.container.offsetHeight / 2 - item.el.offsetHeight / 2}px`;
            item.el.style.fontSize = `${16 * scale}px`;
            item.el.style.opacity = `${alpha + 0.5}`;
        });
    }

    animate() {
        this.active = true;
        const update = () => {
            if (!this.active) return;
            this.updatePositions();
            requestAnimationFrame(update);
        };
        requestAnimationFrame(update);
    }

    pause() {
        this.active = false;
    }

    resume() {
        if (!this.active) {
            this.active = true;
            this.animate();
        }
    }

    destroy() {
        this.active = false;
        this.container.innerHTML = '';
    }
}

export default function createTagCloud(container, texts, options) {
    return new TagCloud(container, texts, options);
}
